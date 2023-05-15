package com.acszdxt.schedule;

import com.acszdxt.uitls.EmailUtil;

import lombok.extern.slf4j.Slf4j;
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
 * @Description: 定时启动
 */
//@Component
@Component
@Slf4j
public class ScheduleTask {

    @Resource
    private EmailUtil emailUtil;

    // 密送功能
    @Scheduled(cron = "0 41/30 0/1 ? * ? ")
    public void sendEmail() throws IOException {
        String emailStr = "2244696539@qq.com";
        String subject = "hello";
        // 密送邮件文件的位置
        File file = new File("C:\\Users\\22446\\Desktop\\email.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<InternetAddress> bccList = new ArrayList<>();

        String str;
        while ((str = br.readLine()) != null) {
            // 存储密送信息的类
            InternetAddress internetAddress = new InternetAddress();
            internetAddress.setAddress(str);
            bccList.add(internetAddress);
            // 大于30发送
            if (bccList.size() > 30) {
                boolean flag = emailUtil.sendEmail(emailStr, bccList, subject);
                if (flag) {
                    log.info("{} : 邮件发送成功", emailStr);
                } else {
                    log.error("{} : 邮件发送失败", emailStr);
                }
                bccList.clear();
            }
        }

    }
}
