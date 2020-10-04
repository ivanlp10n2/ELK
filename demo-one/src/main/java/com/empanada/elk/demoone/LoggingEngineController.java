package com.empanada.elk.demoone;

import com.empanada.elk.logging.LoggingEngine;
import com.empanada.elk.logging.TimerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/engine")
@RestController
public class LoggingEngineController {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingEngineController.class);
  final long TWO_SECONDS = 2 * 1000l ;

  private LoggingEngine engine;

  @Autowired
  LoggingEngineController (LoggingEngine engine){
    this.engine = engine;
  }

  @GetMapping
  @RequestMapping("/start")
  public String start(){
    engine.startNewTimer(TimerDTO.of(TWO_SECONDS, "test"));
    return "Starting new thread logging";
  }

  @GetMapping
  @RequestMapping("/stop")
  public String stop(){
    engine.stopAllTimers();
    return "Stopping all threads";
  }

}
