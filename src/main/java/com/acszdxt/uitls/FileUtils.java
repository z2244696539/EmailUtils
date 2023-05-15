package com.acszdxt.uitls;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 爱吃山楂的小天
 * @Date: 2023/05/12/21:12
 * @Description: 读取邮件内容
 */
@Component
public class FileUtils {
    public String readFile() {
        // 读取邮件html文件
        File file = new File("C:\\Users\\22446\\Desktop\\hello world.html");

        long fileLength = file.length();

        byte[] fileContent = new byte[(int) fileLength];


        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(fileContent);
            inputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(fileContent);
    }
}
