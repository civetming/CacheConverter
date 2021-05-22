package com.mib.converter.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Setter
@Getter
@Table
public class SysUser {

    private Long userId;

    private String userName;

}
