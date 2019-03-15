package com.java.hy.proxy.HYProxy;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class HYProxy {
     //换行符
    public static final String ln = "\r\n";

    public static Object newProxyInstance(HYClassLoader hyClassLoader,Class<?>[] interfaces,HYInvocationHandler invocationHandler){
        try {
            //1、动态生成源代码.java
           String  src = generateSrc(interfaces);
            System.out.println("src"+src);
           //2.JAVA文件输出磁盘
            String filePath = HYProxy.class.getResource("").getPath();
            System.out.println("filePath"+filePath);

            File file = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            //3.把生成的java文件变成class文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
            task.call();
            standardFileManager.close();

            //4.编译生成的class文件加载到JVM中来
            Class<?> $Proxy0 = hyClassLoader.findClass("$Proxy0");
            Constructor<?> constructor = $Proxy0.getConstructor(HYInvocationHandler.class);
            file.delete();

            //返回字节码重组以后的新的代理对象
            return constructor.newInstance(invocationHandler);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer str = new StringBuffer();
        str.append("package com.java.hy.proxy.HYProxy;"+ln);
        str.append("import com.java.hy.proxy.Person;"+ln);
        str.append("import java.lang.reflect.Method;"+ln);
        str.append("public class $Proxy0 implments"+interfaces[0].getName()+"{"+ln);
             str.append("HYInvocationHandler h;"+ln);
             str.append("public $Proxy0(HYInvocationHandler h) {"+ln);
             str.append("this.h = h;");
             str.append("}"+ ln);

             for (Method method : interfaces[0].getMethods()){
                 str.append("public "+method.getReturnType().getName()+" "+method.getName()+"() {"+ln);

                 str.append("try {"+ln);
                 str.append("Method m = "+interfaces[0].getName()+".class.getMethod(\"" +
                         method.getName()+"\n,new Class[]{});");
                           str.append("this.h.invoke(this,m,null);"+ln);
                           str.append("}catch(Throwable e){"+ln);
                           str.append("e.priStackTrace();"+ln);
                           str.append("}");
                 str.append("}");

             }
        str.append("}"+ln);

        return null;
    }
}
