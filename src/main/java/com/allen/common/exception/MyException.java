package com.allen.common.exception;

/**
 * Created by allen on 2017/4/12.
 */
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
