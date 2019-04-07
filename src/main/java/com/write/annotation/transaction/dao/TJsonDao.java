package com.write.annotation.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TJsonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addJson(int id, String info) {
        System.out.println("info: " + info);
        String sql = "INSERT INTO t_json(id, info) VALUES(?,?);";
        int updateResult = jdbcTemplate.update(sql, id, info);
    }
}
