package com.rhb.spring.aop.support.rabbitmq.listener;

import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/20 15:30
 */
public class CustomMessageListenerContainer extends AbstractMessageListenerContainer {

  @Override
  protected void doInitialize() {

  }

  @Override
  protected void doShutdown() {

  }

}
