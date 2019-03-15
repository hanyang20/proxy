package com.java.hy.proxy.HYProxy;

import com.java.hy.proxy.Person;

import java.lang.reflect.Method;

public class HYMeipo implements HYInvocationHandler{


    //被代理的对象,把引用保存下来
    private Person target;

    public Object invoke(Person target) throws Throwable {
        this.target = target;
        Class<? extends Person> aClass = target.getClass();

        return HYProxy.newProxyInstance(new HYClassLoader(),aClass.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(this.target, args);

        after();
        return invoke;
    }

    public void before(){
        System.out.println("我是媒婆,我已经了解了你的需求");
        System.out.println("开始匹配。。。");

    }
    public void after(){
        System.out.println("已经物色好");
        System.out.println("你们可以好好相处下");
    }
}
