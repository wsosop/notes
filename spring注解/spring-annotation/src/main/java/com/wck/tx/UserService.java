package com.wck.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 御香烤翅
 * @create 2020-03-11 21:08
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insert(){
        userDao.insert();
        System.out.println("插入数据完成");
        //int r=10/0;
    }

}
