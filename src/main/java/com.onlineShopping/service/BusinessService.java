package com.onlineShopping.service;

import com.onlineShopping.model.Business;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
public interface BusinessService {

    //检测商家登录
    Business showBusiness(String username, String password);
}
