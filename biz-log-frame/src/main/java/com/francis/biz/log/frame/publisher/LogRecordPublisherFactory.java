package com.francis.biz.log.frame.publisher;

import com.francis.biz.log.frame.LogEntity;
import com.francis.biz.log.frame.LogRecordValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.francis.biz.log.frame.LogRecordValueParser.PREFIX;
import static com.francis.biz.log.frame.LogRecordValueParser.SUFFIX;


/**
 * @author francis
 * @version 2023-08-21
 */
@Component
public class LogRecordPublisherFactory {
    private static final String COMMON = "COMMON";

    private static final Map<String, LogRecordPublisher> SERVICE_MAP = new HashMap<>();
    @Autowired
    private List<LogRecordPublisher> logRecordPublishers;


    @PostConstruct
    private void init() {
        if (CollectionUtils.isEmpty(logRecordPublishers)) {
            return;
        }
        logRecordPublishers.forEach(publisher -> SERVICE_MAP.put(publisher.getBizScenario(), publisher));
    }

    public static LogRecordPublisher get(String bizScenario) {
        LogRecordPublisher logRecordPublisher = SERVICE_MAP.get(bizScenario);
        if (logRecordPublisher == null) {
            logRecordPublisher = SERVICE_MAP.get(COMMON);
        }
        return logRecordPublisher;
    }




}
