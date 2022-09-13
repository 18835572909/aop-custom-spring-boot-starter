package com.rhb.spring.aop.support.rabbitmq.config;

import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现@RabbitListener
 *
 * @author renhuibo
 * @date 2022/9/13 14:23
 */
@Slf4j
public class CustomListenerImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    log.info("EnableCustomRabbit - selector ...");

    return new String[]{};
  }

  @Override
  public Predicate<String> getExclusionFilter() {
    return null;
  }
}
