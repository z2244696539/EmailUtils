package com.acszdxt.uitls;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 爱吃山楂的小天
 * @Date: 2023/05/10/18:00
 * @Description: 发送邮件
 */
@Component
@Slf4j
public class EmailUtil {


    //邮件操作码
    @Value("${spring.mail.password}")
    private String password;
    //字符格式
    @Value("${spring.mail.default-encoding}")
    private String encoding;
    //邮件服务器
    @Value("${spring.mail.host}")
    private String host;
    //源邮箱名
    @Value("${spring.mail.username}")
    private String username;
    //端口号
    @Value("${spring.mail.port}")
    private String port;

    @Resource
    private FileUtils fileUtils;


    /**
     * 发送邮件
     *
     * @param ToEmail 目标邮箱
     * @param subject 主题
     * * @param message 发送消息
     * @param bcc     密送
     */
    public boolean sendEmail(String ToEmail, ArrayList<InternetAddress> bcc, String subject) {
        HtmlEmail email = new HtmlEmail();

        //邮件服务器端口号
        email.setSslSmtpPort(port);
        //email.setSmtpPort(587);
        email.setHostName(host);
        //email.setSSLOnConnect(true);
        //设置原邮箱和邮件操作码
        email.setAuthentication(username, password);
        //设置编码格式
        email.setCharset(encoding);
        try {
            //设置目标邮箱
            email.addTo(ToEmail);
            //设置源邮箱
            email.setFrom(username);
            //设置邮件主题
            email.setSubject(subject);
            //设置邮件内容
            email.setMsg(fileUtils.readFile());
            // 密送成员
            email.setBcc(bcc);
            //发送
            email.send();
            Thread.sleep(1000);
            return true;
        } catch (EmailException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}

