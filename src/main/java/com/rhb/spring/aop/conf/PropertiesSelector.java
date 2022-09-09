package com.rhb.spring.aop.conf;

import com.rhb.spring.aop.properties.BusinessProperties;
import com.rhb.spring.aop.properties.ThirdApiProperties;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 10:35
 */
public class PropertiesSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{
      BusinessProperties.class.getName(), ThirdApiProperties.class.getName()
    };
  }
}
