package com.francis.biz.log.frame;

import lombok.Data;

/**
 * @author francis
 * @version 2023-08-21
 */
@Data
public class UserInfo {
    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 组织Id
     */
    private String orgId;

    /**
     * 组织名
     */
    private String orgName;

    private String ip;
}
