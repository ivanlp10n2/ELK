package com.empanada.elk.demoone.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoggingEngine {
  private static final Logger LOG = LoggerFactory.getLogger(LoggingEngine.class);
  Set<LoggingThread> timersInMemory = new HashSet<>();

  public void startNewTimer(TimerDTO timer) {
    LoggingThread thread = new LoggingThread(timer.getMilliseconds(), timer.getMessage());
     try{
      executeThreads(1, thread);
    } catch (Exception exception){
      LOG.error(exception.getMessage());
    }
    timersInMemory.add(thread);
  }

  public void stopAllTimers(){
    timersInMemory.forEach(LoggingThread::interrupt);
  }

  private void executeThreads(int nThreads, Runnable thread){
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    executorService.execute(thread);
    executorService.shutdown();
  }

}
