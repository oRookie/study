package com.study.thread.juc;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(1);
        list.add("aa");
        list.add("bb");
        System.out.println(list.get(3));
        list.stream().forEach(i-> System.out.println(i));
    }
}
