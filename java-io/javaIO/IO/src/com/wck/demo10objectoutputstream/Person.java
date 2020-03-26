package com.wck.demo10objectoutputstream;

import java.io.Serializable;

/**
 * @author 御香烤翅
 * @create 2020-03-26 17:18
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1161011992012103901L;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
