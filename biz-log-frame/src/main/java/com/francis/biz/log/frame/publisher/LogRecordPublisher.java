package com.francis.biz.log.frame.publisher;

import com.francis.biz.log.frame.LogEntity;
import com.francis.biz.log.frame.LogRecordContext;
import lombok.extern.slf4j.Slf4j;


/**
 * @author francis
 * @version 2023-08-21
 */
@Slf4j
public abstract class LogRecordPublisher extends BasePublisher {
    public static final String COMMON = "COMMON";

    public final void process(LogEntity request) {
        try {
            if (!isValid()) {
                return;
            }
            if (assemble(request)) {
                submit(request);
            }
        } catch (Exception ex) {
            log.error("log publisher error!!!", ex);
        } finally {
            LogRecordContext.clear();
        }
    }

    private void submit(LogEntity request) {
        super.pushlish(request.getPosition(), request.getType(), request.getContent());
    }

    protected abstract boolean assemble(LogEntity request) throws Exception;

    protected abstract String getBizScenario();

    private boolean isValid() {
        return ValidCheck.isValid();
    }

    public Object getPreData() {
        return null;
    }

}
