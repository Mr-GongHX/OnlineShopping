package com.onlineShopping.service;

import com.onlineShopping.model.Business;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 商家接口
 * @Author: Gong Haoxin
 * @CreateTime: 2019-01-11 19:25:50
 **/
public interface BusinessService {

//  检测商家管理员用户名是否重复
    boolean checkShopAdminNameDuplicate(String shopAdminName);

//  商家注册
    int shopRegister(HttpServletRequest request);

//  检测商家登录
    Business showBusiness(String username, String password);
}
