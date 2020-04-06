package myioc03;

import java.lang.reflect.Field;

/*
* Bean包装类，保存bean对象元信息
* */
public class BeanDefinition {
    private Object bean;
    private String scope = "singleton";
    private Class<?> beanClass;
    private String beanClassName;

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

    public Object getBead(){
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
            Field[] fields = beanClass.getFields();
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
}
