package com.minimal.webmagic;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

/**
 * 执行器任务
 *
 * @author linzhiqiang
 * @date 2019/12/17
 */

public class RedisScheduler extends DuplicateRemovedScheduler implements
        MonitorableScheduler, DuplicateRemover {
    @Override
    public int getLeftRequestsCount(Task task) {
        return 0;
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        return false;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return 0;
    }

    private int num = 0;

    @Override
    public Request poll(Task task) {
        Request request = new Request("http://my.oschina.net/flashsword/blog/180623");
        return request;
    }
}