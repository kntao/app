package com.juniorchina.service.imp;

import com.juniorchina.service.UserRequest;
import com.juniorchina.service.UserResponse;
import com.juniorchina.service.UserService;
import org.apache.log4j.net.SyslogAppender;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qingtao.kong on 2016/4/28.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    public UserResponse userInfo(UserRequest request) throws TException {
        UserResponse urp=new UserResponse();
        Map<String,String> params= new HashMap<String,String>();

        urp.setParams(params);

        try{
            if(request.id.equals("10000")){
                urp.setCode("0");
                params= new HashMap<String,String>();
                params.put("name", "lucy-" + request.id);
                urp.setParams(params);
            }
            else {
                urp.setCode("1");
                params= new HashMap<String,String>();
                params.put("name", "lucy-" + request.id);
                urp.setParams(params);
            }
            System.out.println("接收参数是：id="+request.id);
//            Thread.sleep(2 * 1000);
            System.out.println("id:" + request.id + "执行完毕");
            return urp;
        }catch(Exception e){
            e.printStackTrace();
        }
        return urp;
    }

    public void ping() throws TException {
        System.out.println("ping");
    }

    @Override
    public String ping1(String s) throws TException {
        System.out.println("ping:" + s);
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ret:" + s;
    }
}
