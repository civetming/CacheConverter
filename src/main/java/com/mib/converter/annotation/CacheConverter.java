package com.mib.converter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CacheConverter {
    /**
     * @description: 对应适配器的BeanName
     */
    String adapterName() default "";

    /**
     * @description: 对应适配器缓存处理器
     */
    String cacheManager() default "";

    /**
     * @description: 对应依赖的Model里面的属性
     */
    String field();
}
