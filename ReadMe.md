### myioc01

实现了最简单版本的容器，一个可以填装BeanDefinition对象的BeanFactory注册中心

BeanFactory类提供了注册和获取方法

BeanDefinition保存了bean对象的name和value


### myioc02

升级：

1. 将BeanDefinition改成可配置单例或多例模式，默认为单例。修改了BeanDefinition.getBead()方法


### myioc03

升级：

1. 为了扩展性将BeanFactory抽象成接口，具体实现放至AbstractBeanFactory和AutowireCapableBeanFactory，为后续的升级做准备
2. 将bean对象的创建反转至factory框架，只要传className字符串给BeanDefinition，即可在AbstractBeanFactory内部创建bean对象


### myioc04

升级：

1. 目前只能创建一个空构造函数，没有属性赋值的bean对象，这一次我们加上属性


### myioc05

升级：

1. 现在我们来从配置文件中读取bean对象的配置吧，为此新建了io模块，并利用ClassPathXmlApplicationContext类统一管理