package com.onlineShopping.dao;


import com.onlineShopping.model.Business;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description: 商家Dao
 * @Author: Gong Haoxin
 * @CreateTime: 2019-01-13 14:25:50
 **/
public interface BusinessDao {

//  检测商家管理员用户名是否重复
    int isShopAdminNameDuplicate(String shopAdminName);

//  商家注册
    int shopRegister(Business business);

//  商家管理员登录
    Map<String, Object> shopAdminLogin(@Param("shopAdminName") String shopAdminName, @Param("shopAdminPassword") String shopAdminPassword);

//  商家上传头像
    int uploadShopProfile(@Param("shopAdminProfile") String shopAdminProfile,@Param("shopId") String shopId);

//  根据商家id返回商家头像
    String showShopAdminProfile(Integer shopId);

//  根据商家id返回商家信息
    Map<String, Object> shopInfo(Integer shopId);
}
