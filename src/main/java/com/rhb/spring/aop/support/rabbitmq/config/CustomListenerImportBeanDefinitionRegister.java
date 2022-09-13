package com.rhb.spring.aop.support.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/13 14:24
 */
@Slf4j
public class CustomListenerImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {

    log.info("EnableCustomRabbit - register ...");

    registry.registerBeanDefinition("rabbitListenerEndpointRegistry",new RootBeanDefinition());

    registry.registerBeanDefinition("rabbitListenerAnnotationProcessor",new RootBeanDefinition(CustomRabbitListenerAnnotationBeanPostProcessor.class));

  }
}
