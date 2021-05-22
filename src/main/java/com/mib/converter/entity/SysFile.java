package com.mib.converter.entity;

import com.mib.converter.annotation.CacheConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * 描述:
 *
 * @author MIY
 * @create 2021-05-15 16:47
 */
@Setter
@Getter
@Table
public class SysFile {

    private Long id;

    private Long userId;

    @CacheConverter(cacheManager = "userCacheManager", field = "userId")
    private String userName;

}
