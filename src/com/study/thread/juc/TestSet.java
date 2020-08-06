package com.study.thread.juc;

import java.util.HashSet;
import java.util.TreeSet;

public class TestSet {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("2");
        set.add("1");
        set.add("3");
        for(String s:set){
            System.out.println(s);
        }

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("2");
        treeSet.add("1");
        treeSet.stream().forEach(System.out::println);



    }
}
