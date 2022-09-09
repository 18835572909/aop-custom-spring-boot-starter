package com.rhb.spring.aop.conf;

import com.rhb.spring.aop.annotation.CustomComponentScan;
import com.rhb.spring.aop.service.UserService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 *  如果
 *  有ComponentScan，取ComponentScan的basePackages
 *  没有ComponentScan，取当前Import所在类当前包的所有类
 *
 * @author renhuibo
 * @date 2022/9/8 10:52
 */
public class ComponentScan4ImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    String[] basePackages = null;
    if (importingClassMetadata.hasAnnotation(CustomComponentScan.class.getName())) {
      Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(CustomComponentScan.class.getName());
      basePackages = (String[]) annotationAttributes.get("basePackages");
    }
    if (basePackages == null || basePackages.length == 0) {//ComponentScan的basePackages默认为空数组
      String basePackage = null;
      try {
        basePackage = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      basePackages = new String[] {basePackage};
    }
    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    TypeFilter helloServiceFilter = new AssignableTypeFilter(UserService.class);
    scanner.addIncludeFilter(helloServiceFilter);
    Set<String> classes = new HashSet<>();
    for (String basePackage : basePackages) {
      scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
    }
    return classes.toArray(new String[classes.size()]);
  }

}
