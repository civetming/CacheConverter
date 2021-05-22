package com.mib.converter.wrapper;

/**
 * @author ming
 * @className Adapter
 * @description 转换适配器
 * @date 2020/7/5 21:49
 **/
public interface Adapter {
    Object handle(Object originalValue, AdapterConfig adapterConfig);
}
