package com.rhb.spring.aop.ctrl;

import com.rhb.spring.aop.service.OrderService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/8 14:24
 */
//@RestController
@RequestMapping("/order")
public class OrderCtrl {

  @Resource
  OrderService orderService;

  @GetMapping("/getCount")
  public int getOrderCount(){
    return orderService.getOrderCount();
  }

}
