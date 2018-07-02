package com.liuchao.cloud.entities;

import java.math.BigDecimal;

/**
 * 用户实体
 *
 * @author liuchao
 * @date 2018/6/27 21:20
 */
public class User {

    private Long id; // 主键
    private String username; // 用户名
    private String name; // 姓名
    private Integer age; // 年龄
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
