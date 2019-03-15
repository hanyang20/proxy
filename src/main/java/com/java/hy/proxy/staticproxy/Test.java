package com.java.hy.proxy.staticproxy;

import com.gupaoedu.vip.pattern.proxy.Person;
import com.gupaoedu.vip.pattern.proxy.dynamicproxy.jdkproxy.Girl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args){

        final Girl girl = new Girl();
        Person o = (Person)Proxy.newProxyInstance(girl.getClass().getClassLoader(), girl.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("findLove".equals(method.getName())){
                    System.out.println("胸大");
                    return null;
                }
                return method.invoke(girl,args);
            }
        });


    }
}
