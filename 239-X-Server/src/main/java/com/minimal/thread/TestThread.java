package com.minimal.thread;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author linzhiqiang
 * @date 2019/4/18
 */
public class TestThread extends Thread {

    private volatile int flag = 0;

    public TestThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (flag == 0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
//                        .url("https://www.wolzq.com/minimal/requestTest")
                        .url("https://www.wolzq.com/minimal/homeArticles?pageNo=1&pageSize=10")
//                        .url("https://www.wolzq.com/minimal/requestTest")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();
                client.setReadTimeout(60,TimeUnit.SECONDS);
                client.setConnectTimeout(60,TimeUnit.SECONDS);
                com.squareup.okhttp.Response response = client.newCall(request).execute();
//                System.out.println(Thread.currentThread().getName()+":"+response.body().string());
//                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException:"+e.getMessage());
            }
            flag++;
        }
    }
}
