package com.rhb.spring.aop.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/9/14 14:40
 */
@Slf4j
@Component
public class CustomSmartLifecycle implements SmartLifecycle {

  private volatile boolean running = false;

  @Override
  public void start() {
    log.info("custom smart lifecycle start");
    running = true;
  }

  @Override
  public void stop() {
    log.info("custom smart lifecycle stop");
    running = false;
  }

  @Override
  public boolean isRunning() {
    log.info("custom smart lifecycle check running");
    return running;
  }

  /**
   *
   * 如果该`Lifecycle`类所在的上下文在调用`refresh`时,希望能够自己自动进行回调，则返回`true`* ,
   * false的值表明组件打算通过显式的start()调用来启动，类似于普通的Lifecycle实现。
   */
  @Override
  public boolean isAutoStartup() {
    return true;
  }

  /**
   * 当容器停止时，回调该方法。
   * 当执行完你自定义的逻辑后，一定要调用下callback.run(); 这个是为了，告诉容器你已停止自己的组件完成。
   * 这里多说一点，很多源码会在该方法内仅写两行代码，参考上面例子。
   * 一行是stop()；把真正的逻辑转发到stop()这个方法。
   * 另一行就是必须调用的callback.run();
   *
   */
  @Override
  public void stop(Runnable callback) {
    log.info("custom smart lifecycle stop callback");
    stop();
    callback.run();
  }

  @Override
  public int getPhase() {
    return 0;
  }
}
