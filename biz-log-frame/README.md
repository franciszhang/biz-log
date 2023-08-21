### 日志框架使用指南
#### 1.配置
```
#是否开启操作日志
biz.log.enable=true
#执行前校验配置[类和方法名]，以#分割
biz.log.valid-check=class#method
#例如：biz.log.valid-check=com.francis.biz.log.demo.log.OperationUtil#isSuccess
```
#### 2.使用
- 在要记录日志的方法上使用注解 @LogRecord
  - bizScenario表示业务场景，需要唯一
  - needPre表示是否需要操作之前的数据
  - content为日志内容
  - position为操作位置
  - type为操作类型
- 实现LogRecordPublisher抽象
  - getBizScenario和@LogRecord上的bizScenario 保持一致
  - assemble为主要业务逻辑所在，协助完善日志内容、操作位置、操作类型等相关属性设置
  - assemble返回值为 boolean，其中false表示组装失败，不发送日志；相反true刚发送日志
  - 当needPre=true 时，需要重写getPreData()方法
- 日志上下文环境 LogRecordContext
  - LogRecordContext 由InheritableThreadLocal组成，包括了请求参数、返回结果、preData、userInfo 相关信息
  - 请求参数按位置获取，eg: void hello(String hello,String world) ,其中arg0就是 hello变量的值，arg1 就是变量 world 的值
  - 请求参数中会过滤文件流
  - 注意：userInfo 需要手动设置，不设会没有操作用户相关信息
#### 3.其他
- SpEL 表达式：其中用双大括号包围起来的（例如：{{#modifyClass.name}}）#modifyClass.name 是 SpEL表达式。Spring中支持的它都支持的。比如调用静态方法，三目表达式。SpEL 可以使用方法中的任何参数和返回结果
- 框架内置CommonLogRecordPublisher，主要是对日志内容进行SpEL表达式取值来完善日志内容。
- 业务也可自定义CommonLogRecordPublisher，实现LogRecordPublisher即可，通用逻辑日志组装逻辑放在assemble方法中。 注意：getBizScenario返回值必须为"COMMON"。