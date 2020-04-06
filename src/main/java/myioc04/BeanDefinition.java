package myioc04;

import java.lang.reflect.Field;

/*
* Bean包装类，保存bean对象元信息
* */
public class BeanDefinition {
    private Object bean;
    private String scope = "singleton";
    private Class<?> beanClass;
    private String beanClassName;
    private PropertyValues propertyValues;

    public BeanDefinition(String scope) throws Exception {
        if(!scope.equals("singleton") && !scope.equals("prototype")){
            throw new Exception("配置错误");
        }
        this.scope = scope;
    }

    public void setBeanClass(String beanClassName){
        this.beanClassName = beanClassName;
        try {
            beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getBeanClass(){
        return beanClass;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        if(scope.equals("singleton"))
            return bean;
        else {
            Class<?> beanClass = this.bean.getClass();
            Object clone = null;
            try {
                clone = beanClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field:fields
                 ) {
                field.setAccessible(true);
                try {
                    field.set(clone,field.get(this.bean));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return clone;
        }
    }

    public void setPropertyValues(PropertyValues pvs){
        this.propertyValues = pvs;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
