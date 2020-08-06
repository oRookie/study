package com.study.thread.aqs.templatemethod;

public abstract class Cook {
    /**
     * 1.倒油
     */
    void pourOil(){
        System.out.println("倒油");
    };
    /**
     * 2.放辅料
     * 每个菜的辅料不一样
     */
    abstract void putAccessories();

    /**
     * 3.放主材
     */
    abstract void putMainFood();

    /**
     * 4.翻炒出锅
     */
    void finish(){
        System.out.println("翻炒出锅");
    };

    /**
     * 流程
     */
    final void process(){
        pourOil();
        putAccessories();
        putMainFood();
        finish();
    }
}
