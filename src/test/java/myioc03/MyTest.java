package myioc03;

import myioc03.factory.AutowireCapableBeanFactory;
import myioc03.factory.BeanFactory;
import org.junit.Test;

public class MyTest {
    @Test
    public void test() {
        // 创建注入bean对象，目前还是手动创建，未用到IOC控制反转
        //BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        myioc03.BeanDefinition beanDefinition = null;
        try {
            beanDefinition = new myioc03.BeanDefinition("prototype");
            //beanDefinition = new BeanDefinition("singleton");
            beanDefinition.setBeanClass("myioc03.HelloWorldService");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建工厂对象，用来管理注入的bean对象
        BeanFactory factory = new AutowireCapableBeanFactory();
        // 注册
        factory.registerBeanDefinition("helloWorld", beanDefinition);

        // 从factory中获取bean对象
        HelloWorldService helloWorldService1 = (HelloWorldService) factory.getBean("helloWorld");
        HelloWorldService helloWorldService2 = (HelloWorldService) factory.getBean("helloWorld");
        // 调用bean对象方法
        helloWorldService1.hello();
        helloWorldService2.hello();
        // 输出两个对象是否相同
        System.out.println(helloWorldService1 == helloWorldService2);
    }
}
