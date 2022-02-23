package com.hcj.dao;

import com.hcj.domain.User;
import com.hcj.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * UserDao
 * 用户数据库操作类
 *
 * @author hcj
 * @date 2022-02-16
 */
public class UserDao {

    // 创建dbutils的核心类QueryRunner
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    // 通过用户名查询用户
    public User findUserByName(String name) {
        // 创建sql语句
        String sql = "select * from user where username = ?";
        // 设置实际参数
        Object[] params = {name};
        // 创建dbutils的核心接口ResultSetHandler
        BeanHandler<User> bh = new BeanHandler<User>(User.class);
        // 执行查询
        User user = null;
        try {
            user = queryRunner.query(sql, bh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return user;
    }

    // 新增用户
    public void addUser(User user) {
        // 创建sql语句
        String sql = "insert into user(userId,username,password)values(?,?,?)";
        // 设置实际参数
        Object[] params = {user.getUserId(), user.getUsername(), user.getPassword()};
        // 执行更新
        try {
            queryRunner.update(sql, params);
        } catch (SQLException throwables) {
            System.out.println("新增失败，请稍后重试！");
            throwables.printStackTrace();
        }
    }

    /**
     * 根据用户id更新用户
     *
     * @param user 用户对象
     * @return
     */
    public void updateUser(User user) {
        // 创建sql语句
        String sql = "update user set balance = ? where userId = ? ";
        // 设置实际参数
        Object[] params = {user.getBalance(), user.getUserId()};
        // 执行
        try {
            queryRunner.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
