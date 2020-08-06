package com.study.thread.juc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    @Test
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("aaa","aaa");
        map.put("bbb","bbb");
        map.put("ccc","ccc");
        System.out.println(map.get("bbb"));
    }
}
