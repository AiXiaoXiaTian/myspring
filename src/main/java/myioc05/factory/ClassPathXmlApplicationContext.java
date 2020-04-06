package myioc05.factory;

import myioc05.BeanDefinition;
import myioc05.resolver.Resolver;
import myioc05.resolver.XmlResolver;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AutowireCapableBeanFactory {
    private Resolver resolver;

    public ClassPathXmlApplicationContext(String location) {
        this.resolver = new XmlResolver(location);
        loadXml();
    }

    public ClassPathXmlApplicationContext(String location, String scope) {
        this.resolver = new XmlResolver(location, scope);
        loadXml();
    }

    private void loadXml() {
        this.resolver.loadBeanDefinitions();
        Map<String, BeanDefinition> beanDefinitionMap = this.resolver.getRegistry();

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            this.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
