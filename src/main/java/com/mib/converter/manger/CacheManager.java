package com.mib.converter.manger;

import java.util.Map;

/**
 * @author ming
 * @className CacheManager
 * @description 缓存管理器
 * @date 2020/7/6 10:59
 **/
public interface CacheManager<T, R> {

    Object loadCache(T id);

    void updateCache(R... list);

    void deleteCache(T... ids);
}
