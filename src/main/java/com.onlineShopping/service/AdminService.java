package com.onlineShopping.service;

import com.onlineShopping.model.Administrator;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface AdminService {

    //检测管理员登录
    Administrator checkLogin(String username, String password);
}
