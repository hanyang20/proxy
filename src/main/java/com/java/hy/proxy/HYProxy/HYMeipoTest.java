package com.java.hy.proxy.HYProxy;

import com.java.hy.proxy.Person;
import com.java.hy.proxy.jdkproxy.Girl;
import com.java.hy.proxy.jdkproxy.JDKMeipo;
import org.omg.CORBA.TRANSACTION_MODE;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class HYMeipoTest {

    public static void main(String[] args){
        try {
            Person instance = (Person) new JDKMeipo().getInstance(new Girl());
            System.out.println(instance.getClass());
            instance.findLove();


            //通过反编译工具可以查看源代码
            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
            os.write(bytes);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        }
}
