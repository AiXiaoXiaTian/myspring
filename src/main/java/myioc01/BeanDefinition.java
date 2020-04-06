package myioc01;

/*
 * bean对象的包装类，保存bean对象元信息
 * */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBead() {
        return bean;
    }
}
