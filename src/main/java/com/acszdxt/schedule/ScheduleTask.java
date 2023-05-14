package com.acszdxt.schedule;

import com.acszdxt.uitls.EmailUtil;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 爱吃山楂的小天
 * @Date: 2023/05/10/17:20
 * @Description:
 */
//@Component
@Component
public class ScheduleTask {

    @Resource
    private EmailUtil emailUtil;

    @Scheduled(cron = "0 28/30 0/1 ? * ? ")
    public void sendEmail() throws IOException, InterruptedException {
        String emailStr = "2244696539@qq.com";
        File file = new File("C:\\Users\\22446\\Desktop\\aaa.txt");
        File errFile = new File("C:\\Users\\22446\\Desktop\\error.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> errStr = new ArrayList<>();
        ArrayList<InternetAddress> bccList = new ArrayList<>();

        String str ;
        for (int i = 0; i < 20770; i++) {
            str = br.readLine();
            InternetAddress internetAddress = new InternetAddress();
            internetAddress.setAddress(str);
            bccList.add(internetAddress);
            if (i % 50 == 0) {
                boolean flag = emailUtil.sendEmail(emailStr, bccList);
                if (flag) {
                    System.out.println(emailStr + ": 邮件发送成功");
                    Thread.sleep(1000 * 60 * 5);
                } else {
                    System.out.println(emailStr + ": 邮件发送失败");
                    errStr.add(emailStr);
                }
                bccList.clear();
            }
        }
//       while ((str = br.readLine())!=null){
//           InternetAddress internetAddress = new InternetAddress();
//           internetAddress.setAddress(str);
//           bccList.add(internetAddress);
//       }
//        for (String string : strings) {
//            boolean flag = emailUtil.sendEmail(emailStr,bccList);
//            if (flag) {
//                System.out.println(emailStr + ": 邮件发送成功");
//                Thread.sleep(30000);
//            } else {
//                System.out.println(emailStr + ": 邮件发送失败");
//                errStr.add(emailStr);
//            }
////        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(errFile, true));
        for (String s : errStr) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
}
