package com.juniorchina.thrift;

import com.juniorchina.service.UserRequest;
import com.juniorchina.service.UserResponse;
import com.juniorchina.service.UserService;
import org.apache.log4j.net.SyslogAppender;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by qingtao.kong on 2016/4/28.
 */
public class UserServiceRequest implements Runnable {

    private ConnectionProvider connectionProvider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public UserServiceRequest(ConnectionProvider connectionProvider,String id){
        this.connectionProvider = connectionProvider;
        this.id = id;
    }

    public void run() {
        try {
            TSocket socket = connectionProvider.getConnection();
            System.out.println("SSS:" + socket.toString());
            TProtocol protocol = new TBinaryProtocol(socket);
//            TTransport transport = new TSocket("127.0.0.1",7911,2000);
//            TProtocol protocol = new TBinaryProtocol(transport);
//            transport.open();
            UserService.Client client = new UserService.Client(protocol);
//            UserRequest request = new UserRequest();
//            request.setId(id);
//            UserResponse urp = client.userInfo(request);
//            if (urp.code != null && !urp.code.equals("")) {
//                System.out.println("返回代码：" + urp.code + "; 参数是：" + urp.params.get("name"));
//            }
            String ret = client.ping1(id);
            System.out.println("返回代码:" + ret);
        connectionProvider.returnCon(socket);
//            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
