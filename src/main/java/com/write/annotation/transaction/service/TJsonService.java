package com.write.annotation.transaction.service;


import com.write.annotation.transaction.BrianTransaction;
import com.write.annotation.transaction.dao.TJsonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TJsonService {

    @Autowired
    private TJsonDao tJsonDao;

   @BrianTransaction
    public void addJson() throws Exception {
        tJsonDao.addJson(36,"{\"name\":\"许三多\",\"leave\":\"下士\"}");
        int i = 1 / 0;
        tJsonDao.addJson(37,"{\"name\":\"许三多\",\"leave\":\"下士\"}");
    }
}
