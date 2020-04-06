package myioc05;


import myioc05.factory.ClassPathXmlApplicationContext;
import org.junit.Test;

public class MyTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext xmlBeanFactory = new ClassPathXmlApplicationContext("myioc5.xml","prototype");

        HelloWorldService helloWorldService1 = (HelloWorldService)xmlBeanFactory.getBean("helloWorldService");
        HelloWorldService helloWorldService2 = (HelloWorldService)xmlBeanFactory.getBean("helloWorldService");
        helloWorldService1.hello();
        helloWorldService2.hello();
        System.out.println(helloWorldService1 == helloWorldService2);
        LogService logService = (LogService) xmlBeanFactory.getBean("logService");
        System.out.println(logService);
    }
}
