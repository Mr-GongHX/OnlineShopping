package com.onlineShopping.service;

import com.onlineShopping.model.User;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface UserService {

    //检测用户登录
    User checkLogin(String username, String password);
}
