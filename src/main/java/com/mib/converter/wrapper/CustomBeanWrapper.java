package com.mib.converter.wrapper;

import com.mib.converter.manger.CacheManager;
import com.mib.converter.processor.EntityAwareBeanPostProcessor;
import com.mib.converter.utils.ApplicationContextUtils;
import com.sun.org.apache.bcel.internal.generic.Select;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ming
 * @className CustomBeanWrapper
 * @description 自定义处理
 * @date 2020/3/19 17:10
 **/
@Slf4j
public class CustomBeanWrapper extends BeanWrapper {
    private final Reflector reflector;
    private final Object object;

    CustomBeanWrapper(MetaObject metaObject, Object object) {
        super(metaObject, object);
        this.object = object;
        reflector = metaObject.getReflectorFactory().findForClass(object.getClass());
    }


    @Override
    public void set(PropertyTokenizer prop, Object value) {
        if (prop.getIndex() == null) {
            Class<?> modelClass = reflector.getType();
            Map<String, List<AdapterConfig>> adapterConfigMap = EntityAwareBeanPostProcessor.getAdapterConfigMap().get(modelClass);
            String name = prop.getName();
            if (adapterConfigMap != null && adapterConfigMap.containsKey(name)) {
                List<AdapterConfig> adapterConfigs = adapterConfigMap.get(name);
                if (!CollectionUtils.isEmpty(adapterConfigs)) {
                    for (AdapterConfig adapterConfig : adapterConfigs) {
                        String adapterName = adapterConfig.getAdapterName();
                        Object result = null;
                        if (ObjectUtils.isEmpty(adapterName)) {
                            String cacheManagerName = adapterConfig.getCacheManager();
                            if (!ObjectUtils.isEmpty(cacheManagerName)) {
                                CacheManager cacheManager = ApplicationContextUtils.getBean(cacheManagerName);
                                result = cacheManager.loadCache(String.valueOf(value));
                            }
                        } else {
                            Adapter adapter = ApplicationContextUtils.getBean(adapterName);
                            if (adapter == null) {
                                throw new RuntimeException("not find bean name " + adapterName);
                            }
                            result = adapter.handle(value, adapterConfig);
                        }
                        ReflectionUtils.makeAccessible(adapterConfig.getField());
                        ReflectionUtils.setField(adapterConfig.getField(), object, result);
                    }
                }
            }
        }
        super.set(prop, value);
    }

}
