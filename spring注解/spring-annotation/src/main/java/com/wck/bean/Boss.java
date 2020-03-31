package com.wck.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 御香烤翅
 * @create 2020-03-10 20:06
 */
@ToString
@Getter
@Setter
@Component
public class Boss {

    @Autowired
    private Car car;
}
