package com.java.hy.proxy.HYProxy;

import java.lang.reflect.Method;

public interface HYInvocationHandler {

     Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
