package com.onlineShopping.dao;

import com.onlineShopping.model.Administrator;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface AdminDao {

    /**
     * 查找管理员用户名和密码
     * @param username 管理员登录用户名
     * @param password 管理员密码
     * @return
     */
    Administrator findByAdminName(String username);
}
