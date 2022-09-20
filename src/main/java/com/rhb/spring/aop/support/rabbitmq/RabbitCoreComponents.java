package com.rhb.spring.aop.support.rabbitmq;

import lombok.Data;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.CachingConnectionFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.DirectRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionFactoryBeanConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.messaging.Message;
import org.springframework.retry.support.RetryTemplate;

/**
 * rabbitmq核心组件【参照源码】
 *
 * @author renhuibo
 * @date 2022/9/9 15:39
 */
@Data
public class RabbitCoreComponents {

  /**
   * 包装RabbitProperties，所以这里根据我们引入方式，可有可无
   *
   * determineHost->setHost
   * determinePort->setPort
   * determineUsername->setUsername
   * determinePassword->setPassword
   * determineVirtualHost->setVirtualHost
   * ...
   */
  @Deprecated
  private RabbitConnectionFactoryBeanConfigurer rabbitConnectionFactoryBeanConfigurer;

  /**
   * 包装RabbitProperties，所以这里根据我们引入方式，可有可无
   *
   * isPublisherReturns->connectionFactory::setPublisherReturns
   * getPublisherConfirmType->connectionFactory::setPublisherReturns
   * cache.channel.size->connectionFactory::setChannelCacheSize
   * cache.channel.checkoutTimeout->connectionFactory::setChannelCheckoutTimeout
   * cache.connection.mode->connectionFactory::setCacheMode
   * cache.connection.size->connectionFactory::setConnectionCacheSize
   * ...
   */
  @Deprecated
  private CachingConnectionFactoryConfigurer cachingConnectionFactoryConfigurer;

  /**
   * 源码标识是多例模型：prototype
   *  Configuration(proxyBeanMethods = false)
   *
   * 主要是属性赋值，因为属性已经很全，所以沿用源码类，只需要换种properties转connectionFactory的方式
   */
  private CachingConnectionFactory cachingConnectionFactory;



  /********************************************************************************************
   * 1. ConnectionFactory: 配置RabbitMq的链接信息
   * 2. RabbitTemplate: 可以理解为，操作RabbitMq的工具类（当然，你可以再封装）
   * 3. 可以看到所有类都有ConditionalOnMissingBean标签，你懂得，只要我们重新注入自定义的组件，就会覆盖源码的默认值
   ********************************************************************************************/


  /**
   * RabbitTemplateConfigurer除了包装RabbitProperty外，还设置两个重要的组件：消息装换器MessageConverter、重试策略器RetryTemplateCustomizer
   *
   * 同上，只要将配置信息打入RabbitTemplate就可以，所以这个类弃用，并引入MessageConverter\RetryTemplate
   *
   *  RabbitTemplateConfigurer configurer = new RabbitTemplateConfigurer(properties);
   *  configurer.setMessageConverter((MessageConverter)messageConverter.getIfUnique());
   *  configurer.setRetryTemplateCustomizers((List)retryTemplateCustomizers.orderedStream().collect(Collectors.toList()));
   */
  @Deprecated
  private RabbitTemplateConfigurer rabbitTemplateConfigurer;

  /**
   * 组件定义，具体可以通过子类来覆写具体操作方法
   */
  private MessageConverter messageConverter;

  private MessageRecoverer messageRecoverer;

  private RetryTemplate retryTemplate;

  private RabbitTemplate rabbitTemplate;

  /**
   * 主要负责创建queue、exchange、binding
   *
   * {@link RabbitAdmin#initialize()}
   */
  private RabbitAdmin rabbitAdmin;

  /**
   * 看源码这里调用的还是RabbitTemplate，而且这个类基本不怎么用，所以保留默认Bean用（不建议覆写）
   *
   * {@link org.springframework.amqp.rabbit.core.RabbitMessagingTemplate#doSend(String, Message)}  }
   */
  @Deprecated
  private RabbitMessagingTemplate rabbitMessagingTemplate;

  /**
   * configurer转factory
   *
   * {@link org.springframework.boot.autoconfigure.amqp.RabbitAnnotationDrivenConfiguration#simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer, ConnectionFactory, ObjectProvider)}
   */
  @Deprecated
  private SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer;

  private SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory;

  /**
   * configurer转factory
   *
   * {@link org.springframework.boot.autoconfigure.amqp.RabbitAnnotationDrivenConfiguration#directRabbitListenerContainerFactory(DirectRabbitListenerContainerFactoryConfigurer, ConnectionFactory, ObjectProvider)}
   */
  private DirectRabbitListenerContainerFactoryConfigurer directRabbitListenerContainerFactoryConfigurer;

  private DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory;


}
