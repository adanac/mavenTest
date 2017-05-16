package com.allen.common.exception;

/**
 * Created by allen on 2017/4/12.
 */
import java.io.IOException;


public class ExceptionDemo {
    public void f() throws MyException {
        throw new MyException("自定义异常");
    }

    public void g() throws Exception  {
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace();
            throw new Exception("重新抛出的异常1");
        }
    }

    public  void h() throws IOException    {
        try {
            g();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException("重新抛出异常2");
        }
    }
    public static void main(String[] args) {
        try {
            new ExceptionDemo().h();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
