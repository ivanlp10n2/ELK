package com.empanada.elk.demoone.logging;

public class TimerDTO {

  private Long milliseconds;
  private String message;

  private TimerDTO(Long milliseconds, String message) {
    this.milliseconds = milliseconds;
    this.message = message;
  }

  public static TimerDTO of(Long seconds, String message){
    return new TimerDTO(seconds, message);
  }

  public Long getMilliseconds() {
    return milliseconds;
  }

  public String getMessage() {
    return message;
  }
}
