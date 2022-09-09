package com.rhb.spring.aop.annotation.mapperscan;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 15:51
 */
public class MapperRegister implements ImportBeanDefinitionRegistrar {

  final String basePackages = "basePackages";

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    String basePackages = getBasePackages(importingClassMetadata);

    Assert.isTrue(StringUtils.hasLength(basePackages),"@Mapperscan标签缺少basePackages属性");

    ClassPathScanningCandidateComponentProvider scan = new ClassPathScanningCandidateComponentProvider(false);
    addIncludeFilter(scan);
    Set<BeanDefinition> candidateComponents = scan.findCandidateComponents(basePackages);
    for (BeanDefinition beanDefinition : candidateComponents){
      registry.registerBeanDefinition(beanDefinition.getBeanClassName(),beanDefinition);
    }

  }

  private void addIncludeFilter(ClassPathScanningCandidateComponentProvider scan){
    scan.addIncludeFilter(new TypeFilter() {
      @Override
      public boolean match(MetadataReader metadataReader,
          MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 尝试api调用
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        String[] interfaceNames = annotationMetadata.getInterfaceNames();
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        return classMetadata.isConcrete();
      }
    });
  }

  private String getBasePackages(AnnotationMetadata annotationMetadata){
    if(annotationMetadata.hasAnnotation(MapperScan.class.getName())){
      Map<String, Object> annotationAttributes = annotationMetadata
          .getAnnotationAttributes(MapperScan.class.getName());

      if(annotationAttributes !=null && annotationAttributes.containsKey(basePackages)){
        String[] strings = (String[]) annotationAttributes.get(basePackages);
        return strings[0];
      }
    }
    return "";
  }

  /**
   * 这里有点跑题，再看
   *
   * 参考mybatis @link org.mybatis.spring.annotation.MapperScannerRegistrar
   */
  private void annotation2Attr(AnnotationMetadata annotationMetadata,BeanDefinitionRegistry registry){
    AnnotationAttributes annotationAttributes = AnnotationAttributes
        .fromMap(annotationMetadata.getAnnotationAttributes(MapperScan.class.getName()));
//
//    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition()
//
//    List<String> basePackageList = Arrays.stream(annotationAttributes.getStringArray(basePackages)).filter(StringUtils::hasText).collect(Collectors.toList());
//
//    builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackageList));
//
//
//    registry.registerBeanDefinition();
  }

}
