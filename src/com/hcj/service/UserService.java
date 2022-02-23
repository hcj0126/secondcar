package com.hcj.service;

import com.hcj.dao.UserDao;
import com.hcj.domain.User;

/**
 * UserService
 *
 * @author hcj
 * @date 2022-02-16
 */
public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    // 通过用户名查询用户
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    // 新增用户
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * 根据用户id更新用户
     *
     * @param user 用户对象
     * @return
     */
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
