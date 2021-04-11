package com.minimal.webmagic;

import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.pojo.CsdnBlog;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author administrator
 */
public class CsdnBlogTestProcessor implements PageProcessor {

    /**
     * 设置csdn用户名
     */
    private static String username = "yixiao1874";

    /**
     * 共抓取到的文章数量
     */
    private static int size = 0;

    /**
     * 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
     */
    private Site site = Site
            .me()
            .setDomain("csdn.net")
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

    @Override
    public void process(Page page) {
//        System.out.println("html:"+page.getHtml());
//        String regex = "//div[@class='article-list']/div[@class='article-item-box csdn-tracking-statistics']//a//@href";
//        page.putField("title", "测试");
//        int number = 1;
//        while (true) {
//            String regex = "//div[@class='article-list']//div[" + number + "]//a//@href";
//            String content = page.getHtml().xpath(regex).toString();
//            if (StringUtils.isNotBlank(content)) {
////                page.putField("content",content);
//                System.out.println("content" + content);
//            } else {
//                break;
//            }
//            number++;
//        }
//        if (!page.getUrl().regex("http://blog.csdn.net/" + username + "/article/details/\\d+").match()) {
//            //获取当前页码
//            String number = page.getHtml().xpath("//li[@class='page-item active']//a[@class='page-link']/text()").toString();
//            //匹配当前页码+1的页码也就是下一页，加入爬取列表中
//            String targetUrls = page.getHtml().links()
//                    .regex("http://blog.csdn.net/"+username+"/article/list/"+(Integer.parseInt(number)+1)).get();
//            page.addTargetRequest(targetUrls);
//            List<String> detailUrls = page.getHtml().xpath("//li[@class='blog-unit']//a/@href").all();
//            for(String list :detailUrls){
//                System.out.println(list);
//            }
//            page.addTargetRequests(detailUrls);
//        }else {
//            size++;// 文章数量加1
//            CsdnBlog csdnBlog = new CsdnBlog();
//            String path = page.getUrl().get();
//            int id = Integer.parseInt(path.substring(path.lastIndexOf("/")+1));
//            String title = page.getHtml().xpath("//h1[@class='csdn_top']/text()").get();
//            String date = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='time']/text()").get();
//            String copyright = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='original']/text()").get();
//            int view = Integer.parseInt(page.getHtml().xpath("//button[@class='btn-noborder']//span[@class='txt']/text()").get());
//            csdnBlog.id(id).title(title).date(date).copyright(copyright).view(view);
//            System.out.println(csdnBlog);
//        }
//        System.out.println(page.getHtml());
//        String regex = "//div[@class='htmledit_views']";
//        String content = page.getHtml().xpath(regex).toString();
//        System.out.println("content" + content);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CsdnBlogTestProcessor()).addUrl("http://blog.csdn.net/linzhiqiang0316/article/details/103231070")
//                .addPipeline(new CsdnPipeline())
                .thread(1)
                .run();
//        System.out.println("文章总数为"+size);
    }
}