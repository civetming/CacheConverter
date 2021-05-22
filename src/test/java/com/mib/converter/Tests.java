package com.mib.converter;

import com.mib.converter.entity.SysUser;
import com.mib.converter.manger.UserCacheManager;
import com.mib.converter.mapper.FileMapper;
import com.mib.converter.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class Tests {
    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private UserCacheManager userCacheManager;
    @Autowired
    private FileMapper fileMapper;

    @Test
    void addUser() {
        SysUser sysUser = new SysUser();
        Long userId = (long) new Random().nextInt(100);
        sysUser.setUserId(userId);
        sysUser.setUserName("王二狗");
        userCacheManager.updateCache(sysUser);
        UserMapper.addUser(sysUser);
    }


    @Test
    void getFile() {
        System.out.println( fileMapper.select().getUserName());
    }

}
