package com.francis.biz.log.demo;

import com.francis.biz.log.demo.log.IpUtil;
import com.francis.biz.log.frame.LogRecord;
import com.francis.biz.log.frame.LogRecordContext;
import com.francis.biz.log.frame.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@SpringBootApplication
public class BizLogDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizLogDemoApplication.class, args);
    }


    @Autowired
    private HttpServletRequest request;

    public void initUser() {
        UserInfo user = new UserInfo();
        user.setOrgId("orgId");
        user.setOrgName("组织A");
        user.setUserId("francis");
        user.setUserName("用户francis");
        user.setIp(IpUtil.getIP(request));
        LogRecordContext.put(LogRecordContext.USER, user);
    }

    @LogRecord(position = "testCommonLog", type = "UPDATE", content = "【{{#userInfo.userName}}】更新了[testCommonLog]，请求参数是参数1:{{#arg0}},参数2:{{#arg1}}，返回结果:{{#result}}")
    @RequestMapping("/test/common")
    public String testCommonLog(@RequestParam String a, @RequestParam String b) {
        initUser();
        return "success";
    }

    @LogRecord(bizScenario = "testBizLog", position = "testBizLog", type = "UPDATE", needPre = true)
    @RequestMapping("/test/biz/log")
    public String testBizLog(@RequestParam String a, @RequestParam String b) {
        initUser();
        return "success";
    }
}
