package com.java.hy.proxy.jdkproxy;


import com.java.hy.proxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class JDKMeipoTest {

    public static void main(String[] args) throws IOException {
        try {

        Person instance = (Person) new JDKMeipo().getInstance(new Girl());
        instance.findLove();

        //通过反编译工具可以查看源代码
            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
