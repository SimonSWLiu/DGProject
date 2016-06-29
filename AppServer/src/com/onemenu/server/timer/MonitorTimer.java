package com.onemenu.server.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * Title: MonitorTimer.java
 *
 * @author simonliu
 *
 * @data Jun 17, 2015
 * @time 10:52:46 PM
 * 
 *       Description:
 *
 */
public class MonitorTimer extends TimerTask {

    public void run() {

        System.out.println("MonitorOptionTimer is processing ... " + new Date());
    }
}
