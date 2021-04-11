package com.minimal.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


/**
 * @author linzhiqiang
 */
public class CsdnBlogProcessor implements PageProcessor {
    private Site site = Site
            .me()
            .setDomain("csdn.net")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        System.out.println("html:"+page.getHtml());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        RedisScheduler redisScheduler = new RedisScheduler();
        Spider.create(new CsdnBlogProcessor())
                .setScheduler(redisScheduler)
                .addPipeline(new CsdnPipeline())
                .thread(1)
                .run();
    }
}
