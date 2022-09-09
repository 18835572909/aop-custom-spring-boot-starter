package com.rhb.spring.aop.service.impl;

import com.rhb.spring.aop.service.OrderService;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 10:55
 */
public class OrderServiceImpl implements OrderService {

  @Override
  public int getOrderCount() {
    return 10;
  }
}
