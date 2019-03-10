package com.onlineShopping.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员Dao
 * @Author GongHaoxin
 * @Date 2019-02-12 19:16:40
 */
public interface AdminDao {

    /**
     * 查找管理员用户名和密码
     * @param username 管理员登录用户名
     * @param password 管理员密码
     * @return
     */
    int checkAdminLogin(@Param("username") String username, @Param("password") String password);

    //查询平台交易总额
    Object showPlatformTotalTrade();

    //查询平台注册用户总数
    Object showPlatformTotalUsers();

    //查询平台入驻商家总数
    Object showPlatformTotalBusiness();

    //查询平台商品总数
    Object showPlatformTotalGoods();
}
