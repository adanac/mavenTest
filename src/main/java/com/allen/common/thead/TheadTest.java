package com.allen.common.thead;

import com.alibaba.fastjson.JSON;

/**
 * Created by allen on 2017/3/8.
 * 功能描述： 一个线程负责不断的向数据库添加数据，另一个负责对数据进行实时监控
 */
public class TheadTest {
    public static void main(String[] args) {
        // 创建任务
        // 任务一: 读取数据库,给对象赋值
        Person p = new Person();
        Input input = new Input(p);
        // 任务二:将对象的数据取出,保存到数据中
        Output output = new Output(p);

        // 创建线程,一个线程读取数据.
        Thread t1 = new Thread(input);
        // 创建线程,一个线程写出数据
        Thread t2 = new Thread(output);

        t1.start();
        t2.start();
    }
}

/**
 * 该任务模拟读取信息并使用对象存储读取的数据.
 */
class Input implements Runnable {
    Person p;

    Input(Person p) {
        this.p = p;
    }

    int i = 0;

    /**
     * 通过循环,模拟线程不停的从数据库中读取数据,并给对象赋值.
     */
    public void run() {
        while (true) {
            if(i==100){
                System.out.println(JSON.toJSON(i));
                break;
            }
            if (i % 2 == 0) {
                p.write("jack", "male");
            } else {
                p.write("丽丽", "女");
            }
            i++;
        }
    }
}

/**
 * 该任务定义读取对象中的数据,并写出到数据库中.
 */
class Output implements Runnable {
    Person p;

    Output(Person p) {
        this.p = p;
    }

    /**
     * 模拟不停=取出对象的数据,并保存到数据中.
     */
    public void run() {
        while (true) {
            p.read();
        }
    }
}

class Person {
    private String name;
    private String gender;

    // flag 表示开关,默认是断开
    boolean flag = false;

    public synchronized void write(String name, String gender) {
        // 当开关是断开的时候,该线程给对象赋值.
        if (!flag) {
            this.name = name;
            this.gender = gender;

            // 给对象赋值完毕,需要将开关关闭.
            flag = true;
            notify(); // 线程唤醒机制.唤醒线程.
            // 让Input线程给对象赋值完数据,该线程应该等待,不能再执行.
            try {
                this.wait(); // 可以让当前Input 线程等待. 等待Output 读取对象数据.
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * <pre>
     *     使用了wait 和notify 方法,实现了线程的通信.
     *     wait() 线程等待.  释放cpu ,释放锁
     *  notify() 唤醒线程.
     * </pre>
     */
    public synchronized void read() {
        // 如果开关是关闭,读取对象的数据.
        if (flag) {
            System.out.println(this.name + "---" + this.gender);
            // 取完对象的数据,修改开关,将开关断开.
            flag = false;
            // 唤醒线程
            notify();
            // 线程Output 将对象的数据读取完毕后,也应该等待,等待Input再次给对象赋值.
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}