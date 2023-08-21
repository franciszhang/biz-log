package com.francis.biz.log.frame.publisher;

import com.francis.biz.log.frame.LogRecordContext;
import com.francis.biz.log.frame.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;


/**
 * @author francis
 * @version 2023-08-21
 */
@Slf4j
public class BasePublisher {

    void pushlish(String position, String type, String content) {
        //todo biz log operator

        UserInfo user = LogRecordContext.getUser();
        HashMap<String, Object> logContent = new HashMap<>();
        logContent.put("orgId", user == null ? "" : user.getOrgId());
        logContent.put("operateUserId", user == null ? "" : user.getUserId());
        logContent.put("operateUserName", user == null ? "" : user.getUserName());
        logContent.put("ip", user == null ? "" : user.getIp());
        logContent.put("position", position);
        logContent.put("type", type);
        logContent.put("content", content);
        logContent.put("date", new Date());
        log.info("日志记录发送:[{}]", logContent);
    }

}
