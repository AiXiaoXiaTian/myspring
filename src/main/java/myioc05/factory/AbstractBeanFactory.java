package myioc05.factory;

import myioc05.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        if(!beanDefinitionMap.containsKey(name)){
            Object bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
            beanDefinitionMap.put(name,beanDefinition);
        }
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition);
}
