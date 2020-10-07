package com.empanada.elk.app;

import com.empanada.elk.logging.LoggingEngine;
import com.empanada.elk.logging.TimerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/engine")
@RestController
public class LoggingEngineController {

  private final long MILLISECOND = 1000l;

  private LoggingEngine engine;

  @Value("${spring.application.name}")
  private String APP_NAME;

  @Autowired
  LoggingEngineController (LoggingEngine engine){
    this.engine = engine;
  }

  @PostMapping("/start")
  public ResponseEntity<String> start(@RequestBody LoggingConfigurationRequest message){
    Long milliseconds = message.getSeconds() * MILLISECOND;
    TimerDTO timer = TimerDTO.of( milliseconds, message.getMessage());

    engine.startNewTimer(timer);
    return new ResponseEntity("Starting new thread logging in APP: " + APP_NAME, HttpStatus.OK);
  }

  @PostMapping("/stop")
  public ResponseEntity stop(){
    engine.stopAllTimers();
    return new ResponseEntity("Stopping all threads", HttpStatus.OK );
  }

}
