package myioc01;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MyTest {
    @Test
    public void test(){
        // 创建注入bean对象，目前还是手动创建，未用到IOC控制反转
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        // 创建工厂对象，用来管理注入的bean对象
        BeanFactory factory = new BeanFactory();
        // 注册
        factory.registerBeanDefinition("helloWorld",beanDefinition);

        // 从factory中获取bean对象
        HelloWorldService helloWorldService = (HelloWorldService)factory.getBean("helloWorld");
        // 调用bean对象方法
        helloWorldService.hello();
    }


    public void test_get(){
        // 创建工厂对象，用来管理注入的bean对象
        BeanFactory factory = new BeanFactory();

        // 从factory中获取bean对象
        HelloWorldService helloWorldService = (HelloWorldService)factory.getBean("helloWorld");
        // 调用bean对象方法
        helloWorldService.hello();
    }
}
