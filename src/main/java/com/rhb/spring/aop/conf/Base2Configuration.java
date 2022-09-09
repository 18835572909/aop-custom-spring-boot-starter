package com.rhb.spring.aop.conf;

import com.rhb.spring.aop.annotation.CustomComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 10:56
 */
@Component
//@Import({PropertiesSelector.class,ComponentScanImportSelector.class})
@CustomComponentScan(basePackages = {"com.rhb.spring.aop.service"})
public class Base2Configuration {

}
