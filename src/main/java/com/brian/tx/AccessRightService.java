package com.brian.tx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccessRightService {


    @Autowired
    private  AccessRigntDao accessRigntDao;

    @Transactional
    public void  addAccessRight() {
        accessRigntDao.addAccessRight();
        System.out.println("add data successful...");
        int i = 3/0;
    }
}
