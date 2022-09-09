# 重构rabbitmq目的
1. 支持多数据源
```java
custom:
  rabbit:
    server_1:
      host: 127.0.0.1
      port: 5679
      uname: admin
      pswd: admin
      vhost: /order
      listener:
        type: simple\direct\stream
        log:
          # 打印日志
          enable: true
          # 存储日志
          storage: false
        thread-pool:
          # 是否自定义线程池
          type: default\custom
          # 自定义线程池路径
          cla: com.rhb.spring.aop.support.rabbitmq.CustomThreadPool
        simple:
          acknowledge-mode: manual
          # 最小的消费者数量
          concurrency: 1
          # 最大的消费者数量
          max-concurrency: 5
          # 一个消费者最多可处理的nack消息数量，如果有事务的话，必须大于等于transaction数量.
          prefetch: 10
          # 当ack模式为auto时，一个事务（ack间）处理的消息数量，最好是小于等于prefetch的数量.若大于prefetch， 则prefetch将增加到这个值
          transaction-size: 3
          # 决定被拒绝的消息是否重新入队；默认是true（与参数acknowledge-mode有关系）
          default-requeue-rejected: false
          # 发布空闲容器的时间间隔
          idle-event-interval: 5_MIN
          # 若容器声明的队列在代理上不可用，是否失败； 或者运行时一个多或多个队列被删除，是否停止容器
          missing-queues-fatal: false
          retry:
            enable: false
            # 自定义重试策略
            cla: com.rhb.spring.aop.support.rabbitmq.CustomRetryer
            # 最大重试次数
            max-attempts: 3
            # 最大重试时间间隔
            max-interval: 10000
            # 第一次和第二次尝试传递消息的时间间隔
            initial-interval: 3000
            # 应用于上一重试间隔的乘数(加载因子)
            multiplier: 1.5
            # 重试时有状态or无状态
            stateless： false
     server_2:
        ...
     server_3:
        ...
```

2. 模拟spring-boot-starter-amqp

    1. 根据Annotation自动创建queue、exchange、binding
    
    2. 重构@RabbitListener，通过增强处理
  
        1. Annotation支持数据落盘
        
        2. 打印日志、日志落盘
        
        3. 并发处理，支持使用自动以线程池
            
            1. 线程池覆写，支持线程监控
        
        4. 支持设置自定义重试机制