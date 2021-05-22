package com.mib.converter.processor;


import com.mib.converter.annotation.CacheConverter;
import com.mib.converter.mapper.BaseMapper;
import com.mib.converter.wrapper.AdapterConfig;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ming
 * @className EncDecBeanPostProcessor
 * @description Entity注解缓存
 * @date 2020/3/31 9:03
 **/
@Component
public class EntityAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private static Map<Class, Map<String, List<AdapterConfig>>> adapterConfigMap = new HashMap<>();

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof MapperFactoryBean)) {
            return true;
        }
        Class<? extends Class> mapperClass = (Class) ((MapperFactoryBean) bean).getMapperInterface();
        Repository repository = mapperClass.getAnnotation(Repository.class);
        Class<?>[] interfaces = mapperClass.getInterfaces();
        if (interfaces.length <= 0) {
            return true;
        }
        Class<?> superclass = interfaces[0];
        if (repository != null || BaseMapper.class.equals(superclass)) {
            ResolvableType resolvableType = ResolvableType.forClass(mapperClass);
            Class<?> resolve = resolvableType.getInterfaces()[0].getGeneric(0).resolve();
            Map<String, List<AdapterConfig>> fieldMap = new HashMap<>();
            ReflectionUtils.doWithLocalFields(resolve, field -> {
                CacheConverter converter = field.getAnnotation(CacheConverter.class);
                if (converter != null) {
                    List<AdapterConfig> adapterConfigs = fieldMap.computeIfAbsent(converter.field(), k -> new ArrayList<>());
                    AdapterConfig adapterConfig = new AdapterConfig();
                    adapterConfig.setAdapterName(converter.adapterName());
                    adapterConfig.setFieldName(converter.field());
                    adapterConfig.setField(field);
                    adapterConfig.setCacheManager(converter.cacheManager());
                    adapterConfigs.add(adapterConfig);
                }
            });
            adapterConfigMap.put(resolve, fieldMap);
        }
        return true;
    }

    public static Map<Class, Map<String, List<AdapterConfig>>> getAdapterConfigMap() {
        return adapterConfigMap;
    }

}
