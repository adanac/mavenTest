package com.allen.common.serializable;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * Created by allen on 2017/3/10.
 */
public abstract class SerializationUtils {

    /**
     * Serialize the given object to a byte array.
     * @param object the object to serialize
     * @return an array of bytes representing the object in a portable fashion
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return baos.toByteArray();
    }

    /**
     * Deserialize the byte array into an object.
     * @param bytes a serialized object
     * @return the result of deserializing the bytes
     */
    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Failed to deserialize object", ex);
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Failed to deserialize object type", ex);
        }
    }

    /**
     * 将字符串反序列化成对象
     * @param serStr
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object getObjFromStr(String serStr)
            throws UnsupportedEncodingException, IOException,
            ClassNotFoundException {
        String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        Object result = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();

        return result;
    }

    /**
     * 将对象序列化成字符串
     * @param obj
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static String getStrFromObj(Object obj) throws IOException,
            UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 序列化对象到文件
     * @param obj
     * @param filePath
     * @throws IOException
     */
    public static void getFileFromObj(Object obj,String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(objectOutputStream);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * 从文件反序列化对象
     * @param filePath
     */
    public static void getObjFromFile(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object obj =  objectInputStream.readObject();
        System.out.println(JSON.toJSONString(obj));
        objectInputStream.close();
        fileInputStream.close();
    }
}
