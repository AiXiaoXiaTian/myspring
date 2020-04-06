package myioc05;

/*
 * 需要被注入的对象
 * */
public class HelloWorldService {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello world!,I^m " + this.name);
    }
}
