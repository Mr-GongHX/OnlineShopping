package com.onlineShopping.dao;

import com.onlineShopping.model.User;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface UserDao {

    /**
     * 查找用户名和密码
     * @param username 登录用户名
     * @param password 密码
     * @return
     */
    User findByUsername(String username);
}
