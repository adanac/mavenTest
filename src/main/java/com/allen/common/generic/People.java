package com.allen.common.generic;

import java.time.Period;

/**
 * Created by allen on 2017/3/7.
 */
public class People implements Comparable {
    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        People o1 = (People) o;
        return this.getAge() - o1.getAge();
    }
}
