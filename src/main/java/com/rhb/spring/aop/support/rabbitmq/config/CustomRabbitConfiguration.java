package com.rhb.spring.aop.support.rabbitmq.config;

import com.rhb.spring.aop.support.rabbitmq.annotation.ERabbit;
import org.springframework.context.annotation.Configuration;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/13 14:29
 */
@Configuration
public class CustomRabbitConfiguration {

  @ERabbit
  protected static class EnableRabbitConfig{
    protected EnableRabbitConfig(){}
  }

}
