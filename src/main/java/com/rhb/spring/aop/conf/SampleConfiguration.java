package com.rhb.spring.aop.conf;

import com.rhb.spring.aop.properties.BusinessProperties;
import com.rhb.spring.aop.properties.ThirdApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/7 15:48
 */

//@Configuration(proxyBeanMethods = false)
public class SampleConfiguration {

  @Bean
  public ThirdApiProperties thirdApiProperties(){
    return new ThirdApiProperties();
  }

  @Bean
  public BusinessProperties businessProperties(){
    return new BusinessProperties();
  }

}
