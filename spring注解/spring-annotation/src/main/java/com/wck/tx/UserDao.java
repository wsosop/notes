package com.wck.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author 御香烤翅
 * @create 2020-03-11 21:08
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(User user){

        String sql="INSERT INTO tbl_user (username,age) VALUES(?,?)";

        jdbcTemplate.update(user.getUsername(),user.getAge());
    }


    public void insert(){

        String sql="INSERT INTO tbl_user (username,age) VALUES(?,?)";

        String username = UUID.randomUUID().toString().substring(1, 5);

        jdbcTemplate.update(sql,username,18);
    }
}
