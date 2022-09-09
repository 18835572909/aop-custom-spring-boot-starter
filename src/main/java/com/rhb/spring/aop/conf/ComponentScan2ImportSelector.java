package com.rhb.spring.aop.conf;

import com.rhb.spring.aop.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 单个ImportSelector，添加扫描当前标签类所在包
 *
 * @author renhuibo
 * @date 2022/9/8 10:52
 */
public class ComponentScan2ImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    String packageName = null;
    try {
      packageName = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    String[] basePackages = new String[] {packageName};
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
