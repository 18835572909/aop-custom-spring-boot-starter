package com.rhb.spring.aop;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/5 17:01
 */
@RunWith(SpringRunner.class)
@Profile("local")
@SpringBootTest(classes = AopApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseTest {

  @Resource
  RabbitTemplate rabbitTemplate;

  public void t1() throws UnsupportedEncodingException {
    String body = "{\"storeId\":12,\"orderNo\":\"HBDCI000001\",\"shippingTime\":1662034966,\"packageId\":1000001}";

    MessageProperties messageProperties = MessagePropertiesBuilder.newInstance()
        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
        .setContentEncoding("utf-8")
        .setMessageId(String.valueOf(System.currentTimeMillis()))
        .build();

    Message msg = MessageBuilder
        .withBody(body.getBytes("utf-8"))
        .andProperties(messageProperties)
        .build();

    /**
     * 或者
     *  @Autowired
     *  MessageConverter messageConverter;
     *  messageConverter.toMessage(body,messageProperties);
     */

    // 建议：常量定义
    rabbitTemplate.send("order.pay", "order.pay", msg);
  }



}
