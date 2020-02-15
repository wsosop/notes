package com.wck.spring.annotation;

import java.lang.annotation.*;

/**
 * @author 御香烤翅
 * @create 2020-02-15 15:22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default  "";
}
