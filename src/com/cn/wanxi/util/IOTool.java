package com.cn.wanxi.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOTool {
    public static File createFile(String path, String note, String fileName) {
        File filePath = new File(path);
        ;//得到路径对于的文件目录
        File fileNote = new File(filePath, note);//得到想要创建的新的目录
//        fileNote.isDirectory()
        if (!fileNote.exists()) {
            fileNote.mkdirs();//创建文件夹
        }
        File file = new File(fileNote, fileName);//得到文件的路径
        try {
            file.createNewFile();//创建文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void saveFile(File file, String[] data) {
//        try catch捕获异常
        try {
            OutputStream outputStream = new FileOutputStream(file);
            for (int i = 0; i < data.length; i++) {
                outputStream.write(data[i].getBytes(StandardCharsets.UTF_8));
                outputStream.write(PathTool.ENTER.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取数据
     *
     * @param file
     * @return
     */
    public static String readFile(File file) {
        StringBuilder s = new StringBuilder();
        try {
//            得到字节流
            InputStream inputStream = new FileInputStream(file);
//            将字节流转换为字符流
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            int read = 0;//默认读取的字节
//            循环遍历读取的字节
            while ((read = reader.read()) != -1) {
//                读取的字节转换为字符再转换为字符串
                s.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
