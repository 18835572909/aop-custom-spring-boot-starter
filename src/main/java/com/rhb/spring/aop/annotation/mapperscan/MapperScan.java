package com.rhb.spring.aop.annotation.mapperscan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MapperRegister.class)
public @interface MapperScan {

  @AliasFor("value")
  String[] basePackages() default {};

  String[] value() default {};

  String basePackage() default "";

}
