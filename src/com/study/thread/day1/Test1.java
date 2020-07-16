package com.study.thread.day1;

public class Test1 {
    public static void main(String[] args) {
        int x = 500;
        int y = 100;
        int a = x / y;
        int b = 5;
        System.out.println(a + b );
        Thread thread = new Thread();
        thread.interrupt();
    }
}
