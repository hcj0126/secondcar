package com.hcj.domain;

import java.util.LinkedList;

/**
 * 用户类
 * 封装了登录系统后的所有账户信息
 */
public class User {
    // 编号
    private String userId;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 是否是管理员
    private Integer isAdmin;
    // 余额
    private Double balance;
    // 用户加入的对比车辆  一对多设计
    private LinkedList<Car> compareCarList;

    public User() {
        compareCarList = new LinkedList<Car>();
    }

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LinkedList<Car> getCompareCarList() {
        return compareCarList;
    }

    public void setCompareCarList(LinkedList<Car> compareCarList) {
        this.compareCarList = compareCarList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", balance=" + balance +
                ", compareCarList=" + compareCarList +
                '}';
    }
}
