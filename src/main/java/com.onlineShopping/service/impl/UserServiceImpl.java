package com.onlineShopping.service.impl;

import com.onlineShopping.dao.UserDao;
import com.onlineShopping.model.User;
import com.onlineShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 检测用户登录业务
     * @param username
     * @param password
     * @return user
     */
    @Override
    public User checkLogin(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user != null && user.getUserPassword().equals(password)){
            return user;
        }
        return null;
    }

}
