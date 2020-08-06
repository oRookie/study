package com.study.thread.aqs.templatemethod;

public class Food1 extends Cook {
    @Override
    void putAccessories() {
        System.out.println("放入食品1的辅料");
    }

    @Override
    void putMainFood() {
        System.out.println("放入食品1的主材");
    }
}
