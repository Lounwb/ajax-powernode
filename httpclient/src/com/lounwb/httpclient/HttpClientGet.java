package com.lounwb.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpClientGet {
    public static void main(String[] args) throws Exception {
        String channelId = "sdd";
        String clientId = "123";
        // 目标地址
        String url = "https://www.baidu.com";
        HttpGet httpGet = new HttpGet(url);

        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        System.out.println("调用URL: " + httpGet.getURI());

        //httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        HttpResponse response = httpClient.execute(httpGet);
        // ystem.out.println("Response toString()" + response.toString());
        HttpEntity entity = response.getEntity();
        System.out.println("返回状态码：" + response.getStatusLine());


        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        System.out.println("返回消息：" + responseSB);
        reader.close();

        httpClient.close();
    }
}
