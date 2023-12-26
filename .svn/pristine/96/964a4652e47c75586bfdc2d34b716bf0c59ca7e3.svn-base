package com.example.mytest2023.model.design.guancha;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer:  观察者模式
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 * 一对多关系，得当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新。
 * 运用的场景：广播，EventBus等都是观察者模式
 * <p>
 * 主要包括四个部分：
 * 　　1. Subject被观察者。是一个接口或者是抽象类，定义被观察者必须实现的职责，它必须能偶动态地增加、取消观察者，管理观察者并通知观察者。
 * 　　2. Observer观察者。观察者接收到消息后，即进行update更新操作，对接收到的信息进行处理。
 * 　　3. ConcreteSubject具体的被观察者。定义被观察者自己的业务逻辑，同时定义对哪些事件进行通知。
 * 　　4. ConcreteObserver具体观察者。每个观察者在接收到信息后处理的方式不同，各个观察者有自己的处理逻辑。
 * <p>
 * 模拟场景：天气预报，每次天气更新都会向你及时发送消息，观察者们就要更新界面。
 */
public class Weather {
    private int id;
    private String name;

    public Weather() {
    }

    public Weather(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
