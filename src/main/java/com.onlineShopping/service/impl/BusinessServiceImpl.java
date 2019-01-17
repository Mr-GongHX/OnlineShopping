package com.onlineShopping.service.impl;

import com.onlineShopping.dao.BusinessDao;
import com.onlineShopping.model.Business;
import com.onlineShopping.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    /**
     * 检测商家登录业务
     * @param username
     * @param password
     * @return
     */
    @Override
    public Business showBusiness(String username, String password) {
        Business business = businessDao.findByBusinessName(username);
        if(business != null && business.getShopAdminPassword().equals(password)){
            return business;
        }
        return null;
    }
}
