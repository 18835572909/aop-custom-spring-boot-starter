package com.rhb.spring.aop;

import org.junit.runner.RunWith;
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

}
