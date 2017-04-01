package com.allen.common.dto;

/**
 * Created by allen on 2017/3/7.
 */
public class Box<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}