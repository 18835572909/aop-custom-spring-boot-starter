package com.rhb.spring.aop;

import com.rhb.spring.aop.annotation.mapperscan.MapperScan;
import com.rhb.spring.aop.conf.ComponentScanImportSelector;
import com.rhb.spring.aop.conf.PropertiesSelector;
import com.rhb.spring.aop.service.OrderService;
import com.rhb.spring.aop.util.SpringUtil;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/5 16:56
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"com.rhb.spring.aop.mapper"})
public class AopApplication implements CommandLineRunner {

  public static void main(String[] args) {
    // AnnotationConfigServletWebServerApplicationContext
    ConfigurableApplicationContext applicationContext = SpringApplication.run(AopApplication.class, args);

    // DefaultListableBeanFactory
    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getBeanFactory();

    String[] beanDefinitionNames1 = beanFactory.getBeanDefinitionNames();
    log.info("beanFactory.bean_count:{}",beanDefinitionNames1.length);

    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    log.info("bean_count:{}",beanDefinitionNames.length);

    for (String beanDefinitionName : beanDefinitionNames){
      log.info("bean_name: {}",beanDefinitionName);
    }

//
//    BusinessProperties bean = applicationContext.getBean(BusinessProperties.class);
//    BusinessProperties bean1 = applicationContext.getBean(BusinessProperties.class);
//    log.info("bean==bean1 => {}",bean==bean1);
//
//    SampleConfiguration configuration = applicationContext.getBean(SampleConfiguration.class);
//    BusinessProperties businessProperties = configuration.businessProperties();
//    BusinessProperties businessProperties1 = configuration.businessProperties();
//    log.info("bean==bean1 => {}",businessProperties==businessProperties1);

  }

  @Resource
  SpringUtil springUtil;

  @Override
  public void run(String... args) throws Exception {
//    springUtil.registerBeanDefinitions();
    System.out.println("服务启动成功...");
  }

}
