package com.study.thread.aqs.templatemethod;

public class CookMainTest {
    public static void main(String[] args) {
        Food1 food1 = new Food1();
        Food2 food2 = new Food2();
        food1.process();
        food2.process();
    }
}
