package com.rhb.spring.aop.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/7 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdApiProperties {

  private long timeout;

  private long interval;

  private int attempt;

}
