package com.study.thread.juc;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class TestConrrentHashMap {
    @Test
    public void test1(){
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("aaa","aaa");
        System.out.println(concurrentHashMap.get("aaa"));
    }
}
