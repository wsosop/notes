package com.wck.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author 御香烤翅
 * @create 2020-02-15 15:22
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default  "";
    boolean required() default true;
}
