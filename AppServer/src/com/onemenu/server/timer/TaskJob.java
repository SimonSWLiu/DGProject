package com.onemenu.server.timer;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {
    
    @Scheduled(cron = "0 0/1 * * * ?")
    public void job1() {

        System.out.println("TaskJob is processing ... " + new Date());
    }
}
