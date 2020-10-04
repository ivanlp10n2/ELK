package com.empanada.elk.logging;

public class TimerDTO {

  private Long milliseconds;
  private String message;

  private TimerDTO(Long milliseconds, String message) {
    this.milliseconds = milliseconds;
    this.message = message;
  }

  public static TimerDTO of(Long milliseconds, String message){
    return new TimerDTO(milliseconds, message);
  }

  public Long getMilliseconds() {
    return milliseconds;
  }

  public String getMessage() {
    return message;
  }
}
