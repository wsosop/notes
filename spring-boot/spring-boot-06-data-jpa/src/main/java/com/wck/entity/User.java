package com.wck.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 御香烤翅
 * @create 2020-03-17 20:55
 */

//使用JPA注解配置映射关系

@Entity//告诉JPA这是一个实体类（和数据库表映射的类）
@Table(name = "tbl_user")//使用 @Table 来指定和那个数据表对应，如果省略默认表名就是类名小写 如user
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//指定是自增主键
    private Integer id;

    @Column(name = "last_name",length = 50)//这是和数据表对应的一个列
    private String lastName;

    @Column //省略默认的列名就是字段名称
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
