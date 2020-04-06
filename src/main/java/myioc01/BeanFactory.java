package myioc01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Bean创建工厂，目前只有注册和获取功能，需要外部创建好bean对象传进来
 * 每一个bean对象注册后都是单例的
 * */
public class BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBead();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        if (!beanDefinitionMap.containsKey(name)) {
            beanDefinitionMap.put(name, beanDefinition);
        }
    }
}
