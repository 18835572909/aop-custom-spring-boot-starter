package com.rhb.spring.aop.aop;

import com.rhb.spring.aop.aop.service.impl.UserServiceImpl;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/6 16:12
 */
public class CglibMethodInterceptor implements MethodInterceptor {

  private Object obj;

  public Object newInstance(Object obj){
    this.obj = obj;
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(this.obj.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("intercept before ... ");
    /**
     * 为什么异常的时候，会循环调用
     */
    Object invoke = method.invoke(obj, objects);

    System.out.println("intercept after ....");

    return invoke;
  }

  public static void main(String[] args) {
    UserServiceImpl userService = (UserServiceImpl)new CglibMethodInterceptor().newInstance( new UserServiceImpl());
    userService.say();
  }
}
