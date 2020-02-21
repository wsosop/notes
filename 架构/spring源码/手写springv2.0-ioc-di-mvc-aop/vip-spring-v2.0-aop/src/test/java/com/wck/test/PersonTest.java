package com.wck.test;

import java.lang.reflect.Field;

/**
 * @author 御香烤翅
 * @create 2020-02-21 23:26
 */
public class PersonTest {

    public static void main(String[] args) throws Exception{

        Person person=new Person("wck","福建");

        Class<?> clazz=person.getClass();

        Field field=clazz.getDeclaredField("name");
        field.setAccessible(true);
        Object obj=field.get(person);
        System.out.println(obj);




    }
}
