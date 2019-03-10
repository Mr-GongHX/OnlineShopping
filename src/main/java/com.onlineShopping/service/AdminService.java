package com.onlineShopping.service;

import java.util.List;
import java.util.Map;

/**
 * 管理员接口
 * @Author Gong HaoXin
 * @Date 2019-02-12 19:16:40
 */
public interface AdminService {

    //检测管理员登录
    boolean checkAdminLogin(String username, String password);

    //查询平台详细信息
    Map<String, Object> showPlatformDetail();

}
