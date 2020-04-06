package myioc05.factory;

import myioc05.BeanDefinition;
import myioc05.PropertyValue;

import java.lang.reflect.Field;
import java.util.Map;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
            setPropertyValues(beanDefinition, bean);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void setPropertyValues(BeanDefinition beanDefinition, Object bean) throws Exception {
        Map<String, PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        for (Map.Entry<String, PropertyValue> entry : propertyValueList.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue().getValue();
            Field field = bean.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(bean, fieldValue);
        }
    }
}
