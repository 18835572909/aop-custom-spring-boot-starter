package com.rhb.spring.aop.aop;

import com.rhb.spring.aop.aop.service.UserService;
import com.rhb.spring.aop.aop.service.impl.UserServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/6 16:28
 */
public class JDKInvocationHandler implements InvocationHandler {

  private Object object;

  public Object newInstance(Object object){
    this.object = object;
    Object o = Proxy.newProxyInstance(object.getClass().getClassLoader(),
        object.getClass().getInterfaces(), this);
    return o;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("invoke before ...");
    Object invoke = method.invoke(object, args);
    System.out.println("invoke after ... ");
    return invoke;
  }

  public static void main(String[] args) {
    UserServiceImpl userService = new UserServiceImpl();

    BigDecimal a = new BigDecimal(1);
    BigDecimal b = new BigDecimal(2);

    int i = a.add(b).compareTo(BigDecimal.ZERO);

    UserService userService1 = (UserService) new JDKInvocationHandler().newInstance(userService);
    userService1.say();
  }
}
