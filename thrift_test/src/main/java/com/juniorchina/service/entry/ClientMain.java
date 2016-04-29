package com.juniorchina.service.entry;

import com.juniorchina.thrift.ConnectionManager;
import com.juniorchina.thrift.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by qingtao.kong on 2016/4/28.
 */
public class ClientMain {

    @Autowired
    private static ConnectionManager connectionManager;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-thrift-client.xml");
        UserServiceClient userServiceClient = context.getBean(UserServiceClient.class);
        userServiceClient.invoke();
    }
}
