package com.francis.biz.log.frame.publisher;

import com.francis.biz.log.frame.LogEntity;
import com.francis.biz.log.frame.LogRecordValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.francis.biz.log.frame.LogRecordValueParser.PREFIX;
import static com.francis.biz.log.frame.LogRecordValueParser.SUFFIX;

/**
 * @author francis
 * @version 2023-08-21
 * <p>
 * CommonLogRecordPublisher
 * order为0，即优先级最高，便于替换
 */
@Slf4j
@Order(0)
@Component
public class CommonLogRecordPublisher extends LogRecordPublisher {

    @Override
    protected boolean assemble(LogEntity request) throws Exception {
        String content = request.getContent();
        if (StringUtils.hasText(content) && content.contains(PREFIX) && content.contains(SUFFIX)) {
            request.setContent(LogRecordValueParser.parser(content));
        }
        return true;
    }

    @Override
    protected String getBizScenario() {
        return COMMON;
    }

}
