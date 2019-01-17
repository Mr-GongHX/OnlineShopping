package com.onlineShopping.dao;


import com.onlineShopping.model.Business;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface BusinessDao {

    /**
     * 查找商家用户名和密码
     * @param username 商家登录用户名
     * @param password 商家密码
     * @return
     */
    Business findByBusinessName(String username);
}
