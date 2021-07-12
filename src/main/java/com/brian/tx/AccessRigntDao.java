package com.brian.tx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccessRigntDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void  addAccessRight(){
        String sql = "INSERT INTO tb_crc_rpt_access_right (RPAR_REPORT_ID,RPAR_USER,RPAR_USER_NAME,ID) VALUES (?,?,?,1?)";
        jdbcTemplate.update(sql,"12345A","HUANG","Luser",4);
    }

}
