package com.empanada.elk.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingThread extends Thread{

  private static final Logger LOG = LogManager.getLogger(LoggingThread.class);
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
          logMessage();
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

  private void logMessage() {
    LOG.info(message);
  }
}
