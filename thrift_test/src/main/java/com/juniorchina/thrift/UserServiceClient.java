package com.juniorchina.thrift;

import com.juniorchina.service.UserRequest;
import com.juniorchina.service.UserResponse;
import com.juniorchina.service.UserService;
import org.apache.log4j.net.SyslogAppender;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qingtao.kong on 2016/4/28.
 */
@Service
public class UserServiceClient {
    private int count = 150;
    ExecutorService exec = Executors.newFixedThreadPool(150);
    @Autowired
    private ConnectionProvider connectionProvider;

    public void invoke() {
        try {

            List<UserServiceRequest> requests = new ArrayList<UserServiceRequest>();

            int loop = 0;

            while (true) {
                System.out.println("loop:" + loop);
                for (int i = 0; i < count; i++) {
                    int id = 10000  + (loop * 10000);
                    UserServiceRequest request = new UserServiceRequest(connectionProvider,new Integer(id + i).toString());
                    requests.add(request);
                }

                for (UserServiceRequest request : requests) {
                    System.out.println("请求:" + request.getId());
                    exec.execute(request);
                }

                loop ++ ;
                Thread.sleep(5 * 1000);
                requests.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
