package com.acszdxt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 爱吃山楂的小天
 * @Date: 2023/05/10/17:33
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class,args);
    }

}
