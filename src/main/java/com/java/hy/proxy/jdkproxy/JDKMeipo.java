package com.java.hy.proxy.jdkproxy;



import com.java.hy.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeipo implements InvocationHandler {
    //被代理的对象,把引用保存下来
    private Person target;

    public Object getInstance(Person target){
        this.target = target;
        Class<? extends Person> Class = target.getClass();
        return Proxy.newProxyInstance(Class.getClassLoader(),Class.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        //其他方法正常调用,也可以增强
        if ("findLove".equals(method.getName())){
            System.out.println("我需求变了我要找高富帅");
        }
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
