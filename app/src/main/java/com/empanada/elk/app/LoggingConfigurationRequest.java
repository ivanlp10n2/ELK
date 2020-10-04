package com.empanada.elk.app;

public class LoggingConfigurationRequest {
  private int seconds;
  private String message;

  private LoggingConfigurationRequest(int seconds, String message) {
    this.seconds = seconds;
    this.message = message;
  }

  public int getSeconds() {
    return seconds;
  }

  public String getMessage() {
    return message;
  }
}
