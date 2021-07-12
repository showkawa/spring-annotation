package com.write.bjdbc;

import java.sql.Connection;

public interface BrianDbPool {

    //获取连接(重复利用机制)
    public  Connection getConnection();

    //释放连接（可回收机制）
    public void releaseConnection(Connection connection);
}
