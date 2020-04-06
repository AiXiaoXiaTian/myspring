package myioc02;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/*
 * bean对象包装类，保存bean对象元信息
 * */
public class BeanDefinition {
    private Object bean;

    private String scope = "singleton";

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public BeanDefinition(Object bean, String scope) throws Exception {
        if (!scope.equals("singleton") && !scope.equals("prototype")) {
            throw new Exception("配置错误");
        }
        this.bean = bean;
        this.scope = scope;
    }

    public Object getBead() {
        if (scope.equals("singleton"))
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
            for (Field field : fields
            ) {
                field.setAccessible(true);
                try {
                    field.set(clone, field.get(this.bean));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return clone;
        }
    }
}
