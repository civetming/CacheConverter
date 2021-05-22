package com.mib.converter.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * @author ming
 * @className AdapterConfig
 * @description 适配配置
 * @date 2020/7/6 13:35
 **/
@Getter
@Setter
public class AdapterConfig {
    private String adapterName;
    private String fieldName;
    private Field field;
    private String cacheManager;
}
