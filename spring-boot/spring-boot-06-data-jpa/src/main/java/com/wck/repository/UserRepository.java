package com.wck.repository;

import com.wck.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 御香烤翅
 * @create 2020-03-17 21:05
 */

//继承jpaRepository 来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {



}
