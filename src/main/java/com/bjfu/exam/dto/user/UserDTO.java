package com.bjfu.exam.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号类型
     */
    private Integer type;
    /**
     * 账号状态
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后修改时间
     */
    private Date lastModifiedTime;
}
