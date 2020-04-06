package myioc04.factory;

import myioc04.BeanDefinition;

/*
* Bean创建工厂，目前只有注册和获取功能，需要外部创建好bean对象传进来
* 每一个bean对象注册后都是单例的
* */
public interface BeanFactory {
    Object getBean(String name);
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
