package com.wck.service;

import com.wck.dao.BookDao;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author 御香烤翅
 * @create 2020-03-09 19:50
 */

@Service
@ToString
public class BookService {

//    @Qualifier("bookDao")
//    @Autowired
//    @Resource
    @Inject
    private BookDao bookDao;



}
