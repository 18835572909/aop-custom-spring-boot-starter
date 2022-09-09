package com.rhb.spring.aop.sourceutil;

import com.rhb.spring.aop.properties.BusinessProperties;
import com.rhb.spring.aop.properties.ThirdApiProperties;
import org.junit.Test;
import org.springframework.boot.context.properties.PropertyMapper;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/9 17:02
 */
public class SourceUtil {

  @Test
  public void t1(){
    PropertyMapper propertyMapper = PropertyMapper.get();

    BusinessProperties businessProperties = new BusinessProperties();
    businessProperties.setTryCount(1);

    ThirdApiProperties thirdApiProperties = new ThirdApiProperties();
    propertyMapper.from(businessProperties::getTryCount).whenNonNull().to(thirdApiProperties::setAttempt);

    System.out.println(thirdApiProperties.getAttempt());
  }

}
