package com.wck.spring.annotation;

import java.lang.annotation.*;

/**
 * @author 御香烤翅
 * @create 2020-02-15 15:21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowried {
    String value() default  "";
}
