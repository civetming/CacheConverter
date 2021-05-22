package com.mib.converter.manger;


import com.mib.converter.constants.CacheConstants;
import com.mib.converter.entity.SysUser;
import com.mib.converter.utils.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * @author ming
 * @className UserCacheManager
 * @description 用户缓存管理器
 * @date 2020/7/6 10:36
 **/
@Component
public class UserCacheManager implements CacheManager<String, SysUser> {
    @Override
    public Object loadCache(String userId) {
        String userName = RedisUtils.get(CacheConstants.CACHE_USER_NAME + userId);
        if (ObjectUtils.isEmpty(userName)) {
            return userId;
        } else {
            return userName;
        }
    }


    @Override
    public void updateCache(SysUser... users) {
        if (CollectionUtils.isEmpty(Arrays.asList(users))) {
            return;
        }
        for (SysUser user : users) {
            RedisUtils.set(CacheConstants.CACHE_USER_NAME + user.getUserId(), user.getUserName());
        }
    }


    @Override
    public void deleteCache(String... userIds) {
        if (CollectionUtils.isEmpty(Arrays.asList(userIds))) {
            return;
        }
        for (String userId : userIds) {
            RedisUtils.del(CacheConstants.CACHE_USER_NAME + userId);
        }
    }

}
