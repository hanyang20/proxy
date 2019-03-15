package com.java.hy.proxy.jdkproxy;


import com.java.hy.proxy.Person;

public class Girl implements Person {
    @Override
    public int findLove() {
        System.out.println("要求:三个180");
        System.out.println("8块腹肌");
        return 0;
    }
}
