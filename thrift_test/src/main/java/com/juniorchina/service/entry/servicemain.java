package com.juniorchina.service.entry;

import com.juniorchina.thrift.UserServiceServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by qingtao.kong on 2016/4/28.
 */
public class ServiceMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-thrift-server.xml");
        UserServiceServer server = context.getBean(UserServiceServer.class);
        server.start();
    }
}
