package com.write.annotation.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
@Scope("prototype") //多例模式
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transactionStatus;

    /**
     * 开启事务
     * @return
     */
    public TransactionStatus begin() {
        System.out.println("----------------: 开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 事务回滚
     *
     */
    public void rollback(){
       if(this.transactionStatus != null){
           System.out.println("----------------: 事务回滚");
           dataSourceTransactionManager.rollback(this.transactionStatus);
       }

    }
}
