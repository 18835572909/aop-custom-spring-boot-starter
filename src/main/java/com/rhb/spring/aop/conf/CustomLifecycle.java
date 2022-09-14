package com.rhb.spring.aop.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/14 14:18
 */
@Component
@Slf4j
public class CustomLifecycle implements Lifecycle {

  private volatile boolean running = false;

  @Override
  public void start() {
    log.info("custom lifecycle start ...");
    running = true;
  }

  @Override
  public void stop() {
    log.info("custom lifecycle stop...");
    running = false;
  }

  @Override
  public boolean isRunning() {
    log.info("custom lifecycle check running ...");
    return running;
  }

}
