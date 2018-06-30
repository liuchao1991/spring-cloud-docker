package com.liuchao.cloud.entites;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 用户实体
 *
 * @author liuchao
 * @date 2018/6/27 21:20
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键
    @Column
    private String username; // 用户名
    @Column
    private String name; // 姓名
    @Column
    private Integer age; // 年龄
    @Column
    private BigDecimal balance; // 余额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
