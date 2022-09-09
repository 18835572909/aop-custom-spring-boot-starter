package com.rhb.spring.aop.aop.service.impl;

import com.rhb.spring.aop.aop.service.UserService;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/5 17:10
 */
public class UserServiceImpl implements UserService {

  @Override
  public void say() {
    System.out.println("hello");
  }

}
