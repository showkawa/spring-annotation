import com.write.bjdbc.BrianDbPool;
import com.write.bjdbc.BrianDbPoolImpl;

import java.sql.Connection;

/**
 * 手写数据库连接池 (核心参数：空闲线程，核心线程)
 * 1.初始化线程池(初始化空闲线程)
 * 2.调用getConnection方法  -->获取连接
 * ####2.1 先去空闲线程里面获取Connection,存放到活动线程里面去 、
 * 3.调用releaseConnection方法 -->释放连接  --> 资源回收
 *####3.1 获取活动线程的Connection,将其转移到空闲线程里面取
 *
 */
public class JDBC_TEST_CONNECT {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i <5; i++) {
            new Thread(threadPool).start();
        }
    }
}

class ThreadPool implements Runnable {

    @Override
    public void run() {
        BrianDbPool brianDbPool = new BrianDbPoolImpl();
        for (int i = 0; i <100; i++) {
            Connection connection = brianDbPool.getConnection();
            System.out.println(Thread.currentThread().getName() + " <<>> " + connection);
            brianDbPool.releaseConnection(connection);
        }
    }
}