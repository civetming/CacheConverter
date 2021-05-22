package com.mib.converter.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import javax.persistence.Table;

/**
 * @author ming
 * @className CustomObjectWrapperFactory
 * @description 包装类
 * @date 2020/3/19 17:13
 **/
public class CustomWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        Table annotation = object.getClass().getAnnotation(Table.class);
        if (annotation == null) {
            Class<?> superclass = object.getClass().getSuperclass();
            if (superclass == null) {
                return false;
            }
            annotation = superclass.getAnnotation(Table.class);
            return annotation != null;
        } else {
            return true;
        }
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new CustomBeanWrapper(metaObject, object);
    }
}
