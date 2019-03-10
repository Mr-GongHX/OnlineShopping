package com.onlineShopping.service.impl;

import com.onlineShopping.dao.AdminDao;
import com.onlineShopping.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员实现类
 * @Author Gong HaoXin
 * @Date 2019-02-12 19:16:40
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    /**
     * 检测管理员登录业务
     * @param username
     * @param password
     * @return admin
     */
    @Override
    public boolean checkAdminLogin(String username, String password) {
        if(adminDao.checkAdminLogin(username, password) == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查询平台详细信息
     * @return
     */
    @Override
    public Map<String, Object> showPlatformDetail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "totalTrade", adminDao.showPlatformTotalTrade());
        map.put("totalUsers", adminDao.showPlatformTotalUsers());
        map.put("totalBusinesses", adminDao.showPlatformTotalBusiness());
        map.put("totalGoods", adminDao.showPlatformTotalGoods());
        return map;
    }

}
