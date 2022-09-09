package com.rhb.spring.aop.conf;

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
 * ComponentScan + Import在一起
 *
 * @author renhuibo
 * @date 2022/9/8 10:52
 */
public class ComponentScanImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(
        ComponentScan.class.getName());
    String[] basePackages = (String[]) annotationAttributes.get("basePackages");
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
