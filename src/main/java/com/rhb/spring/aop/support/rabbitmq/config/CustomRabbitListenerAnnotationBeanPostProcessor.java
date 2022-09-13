package com.rhb.spring.aop.support.rabbitmq.config;

import com.rhb.spring.aop.support.rabbitmq.annotation.RListener;
import com.rhb.spring.aop.support.rabbitmq.metadata.RabbitListenerMetadata;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 *
 *
 * @author renhuibo
 * @date 2022/9/13 14:36
 */
@Slf4j
public class CustomRabbitListenerAnnotationBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    log.info("postProcessBeforeInitialization...");
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    log.info("postProcessAfterInitialization...");

    /**
     * 这里罗列下思路：
     *  1. 包扫描，捕捉带有注解的类
     *  2. 针对RListener的两种情况处理：
     *      a. 在类层面
     *      b. 在方法层面
     */
    String basePackage = "com.rhb.spring.aop.support.rabbitmq";
    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    TypeFilter includeFilter = new AnnotationTypeFilter(RListener.class);
    provider.addIncludeFilter(includeFilter);
    Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(basePackage);

    for (BeanDefinition definition : candidateComponents){
      /**
       * 将Annotation信息封装成metadata
       */
      RabbitListenerMetadata rabbitListenerMetadata = buildMetadata();
      /**
       * 一下相关注解，由于是自定义标签，模拟源码，所以用自定义Annotation说明
       *
       * processAmqpListener： 处理RListener在方法上
       *
       * processMultiMethodListeners： 处理RListener在类上，RHandler在方法上
       */
      if(atType()){
        processMultiMethodListeners(rabbitListenerMetadata);
      }else {
        processAmqpListener(rabbitListenerMetadata);
      }
    }

    return null;
  }

  private boolean atType(){
    return false;
  }

  /**
   * @RListener 转metadata处理
   *
   * Spring中常见的：
   * 1. yml文件配置，转Property或者XXXConfiguration
   * 2. Annotation转Metadata
   */
  private RabbitListenerMetadata buildMetadata(){
    return null;
  }

  /**
   * 处理RListener在方法上
   */
  protected void processAmqpListener(RabbitListenerMetadata rabbitListenerMetadata){
    /**
     * 1. 实现aop编程
     * 2. 实现事件监听
     */
  }

  /**
   * 处理RListener在类上，RHandler在方法上
   */
  protected void processMultiMethodListeners(RabbitListenerMetadata rabbitListenerMetadata){

  }

}
