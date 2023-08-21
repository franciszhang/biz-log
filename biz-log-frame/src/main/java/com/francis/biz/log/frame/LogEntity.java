package com.francis.biz.log.frame;

import lombok.Builder;
import lombok.Data;


/**
 * @author francis
 * @version 2023-08-21
 */
@Data
@Builder
public class LogEntity {
    /**
     * 操作日志内容
     */
    private String content;
    /**
     * 操作位置
     */
    private String position;
    /**
     * 操作类型
     */
    private String type;

}
