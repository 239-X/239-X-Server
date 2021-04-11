package com.minimal.webmagic;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author linzhiqiang
 * @date 2019/12/16
 */
public class CsdnPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        //文章题目
        String title = resultItems.get("title");
        //文章内容
        String context = resultItems.get("context");
        System.out.println("title:" + title);
        System.out.println("context:" + context);
    }
}