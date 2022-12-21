package com.lounwb.fastjson;

import com.alibaba.fastjson.JSON;

public class FastJsonTest {
    public static void main(String[] args) {
        User user = new User("111","zhangsan",20);

        String s = JSON.toJSONString(user);
        System.out.println(s);
    }
}
