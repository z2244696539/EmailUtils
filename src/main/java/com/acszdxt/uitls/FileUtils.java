package com.acszdxt.uitls;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 爱吃山楂的小天
 * @Date: 2023/05/12/21:12
 * @Description:
 */
@Component
public class FileUtils {
   public   String readFile()  {
       String encoding = "UTF-8";
       File file = new File("C:\\Users\\22446\\Desktop\\at59z-no2su.html");
       Long filelength = file.length();


       byte[] filecontent = new byte[filelength.intValue()];


       FileInputStream inputStream = null;
       try {
           inputStream = new FileInputStream(file);
           inputStream.read(filecontent);
           inputStream.close();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


       String string = new String(filecontent);
       return string;
   }
}
