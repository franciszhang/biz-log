package com.francis.biz.log.demo.log;

import com.francis.biz.log.frame.LogEntity;
import com.francis.biz.log.frame.LogRecordContext;
import com.francis.biz.log.frame.publisher.LogRecordPublisher;
import org.springframework.stereotype.Component;

/**
 * @author francis
 * @version 2023-08-21
 */
@Component
public class BizLogRecordPublisher extends LogRecordPublisher {
    @Override
    protected boolean assemble(LogEntity request) throws Exception {
        request.setContent("更新了xxxx，参数1为："+LogRecordContext.get(LogRecordContext.ARG_0)+
                "，参数2为："+LogRecordContext.get(LogRecordContext.ARG_1)+"，操作结果为："+LogRecordContext.getResult()
                +"，操作前数据为【"+ LogRecordContext.getPre()+"】");
        return true;
    }

    @Override
    protected String getBizScenario() {
        return "testBizLog";
    }

    @Override
    public Object getPreData() {
        return "testBizLog-preData";
    }
}
