package com.study.thread.aqs.templatemethod;

public class Food2 extends Cook {

    @Override
    void putAccessories() {
        System.out.println("放入食品2的辅料");
    }

    @Override
    void putMainFood() {
        System.out.println("放入食品2的主材");
    }
}
