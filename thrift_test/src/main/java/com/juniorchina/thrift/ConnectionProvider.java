package com.juniorchina.thrift;

import org.apache.thrift.transport.TSocket;

/**
 * Created by qingtao.kong on 2016/4/28.
 */

/**
 * 连接池接口
 *
 */
public interface ConnectionProvider {
    /**
     * 取链接池中的一个链接
     * @return TSocket
     */
    TSocket getConnection();

    /**
     * 返回链接
     * @param socket
     */
    void returnCon(TSocket socket);
}
