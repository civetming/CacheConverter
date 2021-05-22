package com.mib.converter.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {
    private static RedisTemplate redisTemplate;

    @Autowired
    public void setStringRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }
    /**
     * 得到value
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
    /**
     * 设置key value
     *
     * @param key
     * @return
     */
    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * 设置key value,过期时间单位为秒
     *
     * @param key
     * @return
     */
    public static void set(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }
    /**
     * 删除
     *
     * @param key
     */
    public static Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}
