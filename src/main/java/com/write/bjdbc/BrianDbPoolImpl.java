package com.write.bjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库连接池
 * 1.初始化线程池(初始化空闲线程)
 * 2.调用getConnection方法  -->获取连接
 * ####2.1 先去空闲线程里面获取Connection,存放到活动线程里面去 、
 * 3.调用releaseConnection方法 -->释放连接  --> 资源回收
 * ####3.1 获取活动线程的Connection,将其转移到空闲线程里面取
 */
public class BrianDbPoolImpl implements BrianDbPool{


    private List<Connection> freeConnections = new Vector<>();

    private  List<Connection> activeConnections = new Vector<>();

    private AtomicInteger count = new AtomicInteger();

    public BrianDbPoolImpl() {
        init();
    }

    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;
        //小于最大空闲连接数
        if(count.get() < DbBean.maxActiveConnections){
            //判断空闲线程集合是否有数据
            if(freeConnections.size() >0 ){
                //获取当前连接并移除
                 connection = freeConnections.remove(0);
            }else {
                connection = newConnection();
            }

            //判断连接是否可用
            if(isAvailable(connection)){
                //将连接存放到活动线程集合中
                activeConnections.add(connection);
            }else {
                count.decrementAndGet();
                connection = getConnection();
            }

        }else{
            //小于最大空闲连接数，进行等待
            try {
                wait(DbBean.connTimeOut);
                //重试
                connection = getConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        //判断连接是否可用
        if(isAvailable(connection)){
            //判断空闲线程集合是否已经满了
            if(freeConnections.size() < DbBean.maxConnections) {
                freeConnections.add(connection);
            } else {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            activeConnections.remove(connection);
            count.decrementAndGet();
            notifyAll();

        }


    }


    //初始化线程池(初始化空闲线程)
    private void init() {
        //1.获取初始化连接数
        for (int i = 0; i < DbBean.initConnections; i++) {
            //2.创建Connection连接
            Connection connection = newConnection();
            if(connection != null){
                //3.放在空闲线程freeConnections集合里面
                freeConnections.add(connection);
            }
        }
    }

    private Connection newConnection(){
        try {
            Class.forName(DbBean.driverName);
            Connection connection = DriverManager.getConnection(DbBean.url, DbBean.userName, DbBean.password);
            count.incrementAndGet();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isAvailable(Connection connection) {
        try {
            if(connection == null || connection.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
