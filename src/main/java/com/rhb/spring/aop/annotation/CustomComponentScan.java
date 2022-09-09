package com.rhb.spring.aop.annotation;

import com.rhb.spring.aop.conf.ComponentScan4ImportSelector;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 11:40
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ComponentScan4ImportSelector.class)
public @interface CustomComponentScan {

  @AliasFor("value")
  String[] basePackages() default {};

  String basePackage() default "";

  String[] value() default {};

}
