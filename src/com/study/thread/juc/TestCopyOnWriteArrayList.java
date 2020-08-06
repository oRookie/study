package com.study.thread.juc;

import java.util.concurrent.CopyOnWriteArrayList;

public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("aaa");
        list.add("bbb");
        System.out.println(list.get(3));
        list.stream().forEach(i-> System.out.println(i));
    }
}
