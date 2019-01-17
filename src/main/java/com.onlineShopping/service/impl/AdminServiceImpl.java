package com.onlineShopping.service.impl;

import com.onlineShopping.dao.AdminDao;
import com.onlineShopping.model.Administrator;
import com.onlineShopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 检测管理员登录业务
     * @param username
     * @param password
     * @return admin
     */
    @Override
    public Administrator checkLogin(String username, String password) {
        Administrator admin = adminDao.findByAdminName(username);
        if(admin != null && admin.getAdminPassword().equals(password)){
            return admin;
        }
        return null;
    }
}
