package com.rhb.spring.aop.support.rabbitmq.annotation;

import com.rhb.spring.aop.support.rabbitmq.config.CustomListenerImportBeanDefinitionRegister;
import com.rhb.spring.aop.support.rabbitmq.config.CustomListenerImportSelector;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/13 14:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {CustomListenerImportBeanDefinitionRegister.class, CustomListenerImportSelector.class})
public @interface EnableCustomRabbit {

}
