package myioc05.resolver;

import myioc05.BeanDefinition;

import java.util.Map;

public interface Resolver {
    void loadBeanDefinitions();
    Map<String, BeanDefinition> getRegistry();
}
