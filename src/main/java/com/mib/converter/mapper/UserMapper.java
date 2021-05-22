package com.mib.converter.mapper;

import com.mib.converter.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @Author: Ming
 * @Date: 2021/5/15 13:50
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<SysUser> {
    void addUser(SysUser sysUser);
}
