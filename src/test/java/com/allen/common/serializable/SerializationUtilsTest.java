package com.allen.common.serializable;

import com.alibaba.fastjson.JSON;
import com.allen.common.dto.UserDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by allen on 2017/3/10.
 */
public class SerializationUtilsTest {
    @Test
    public void getFileFromObj() throws Exception {
        String filePath = "D:\\temp\\user.txt";
        SerializationUtils.getFileFromObj(user,filePath);
    }

    @Test
    public void getObjFromFile() throws Exception {

        String filePath = "D:\\temp\\user.txt";
        Object obj = SerializationUtils.getObjFromStr(filePath);
        System.out.println(JSON.toJSONString(obj));
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        strTemp = null;
    }

    private UserDto user;
    private String strTemp;

    @Before
    public void setUp() throws Exception {
        user = new UserDto("frank", 11);
    }

    @Test
    public void getObjFromStr() throws Exception {
        System.out.println(strTemp);
        strTemp = SerializationUtils.getStrFromObj(user);
        System.out.println(strTemp);
    }

    @Test
    public void getStrFromObj() throws Exception {
        strTemp = SerializationUtils.getStrFromObj(user);
        user = (UserDto) SerializationUtils.getObjFromStr(strTemp);
        System.out.println(user.toString());
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * ByteArrayOutputStream把内存中的数据读到字节数组中,
     * 而ByteArrayInputStream又把字节数组中的字节以流的形式读出,
     * 实现了对同一个字节数组的操作
     *
     * @throws Exception
     */
    @Test
    public void testAll() throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);
        String name = "XYY";
        int age = 27;
        dout.writeUTF(name);
        dout.writeInt(age);
        byte[] buff = bout.toByteArray();
        ByteArrayInputStream bin = new ByteArrayInputStream(buff);
        DataInputStream dis = new DataInputStream(bin);
        String newName = dis.readUTF();
        int newAge = dis.readInt();
        System.out.println(newName + ":" + newAge);
    }

    @Test
    public void serialize() throws Exception {
        UserDto user = new UserDto("allen", 25);
        byte[] bytes = SerializationUtils.serialize(user);
        System.out.println(JSON.toJSONString(bytes));
        Object obj = SerializationUtils.deserialize(bytes);
        System.out.println(JSON.toJSONString(obj));
    }

    @Test
    public void deserialize() throws Exception {

    }

}