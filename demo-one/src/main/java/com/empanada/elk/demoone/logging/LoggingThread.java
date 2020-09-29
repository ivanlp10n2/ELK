package com.empanada.elk.demoone.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingThread extends Thread{

  private static final Logger LOG = LoggerFactory.getLogger(LoggingThread.class);
  private boolean done = false;
  private Long milliseconds;
  private String message;

  public LoggingThread(Long milliseconds, String message) {
    this.milliseconds = milliseconds;
    this.message = message;
  }

  @Override
  public void run() {
    while (!done) {
      try {
          doWork();
          Thread.sleep(milliseconds);
      } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  public void interrupt() {
    this.finishExecution();
  }

  public void finishExecution() {
    this.done = true;
  }

  private void doWork() {
    LOG.info(message);
  }
}
