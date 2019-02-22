package com.onlineShopping.service.impl;

import com.onlineShopping.dao.BusinessDao;
import com.onlineShopping.model.Business;
import com.onlineShopping.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 商家实现类
 * @Author: Gong Haoxin
 * @CreateTime: 2019-01-12 19:25:50
 **/
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    /**
     * 检测商家管理员用户名是否重复
     * @param shopAdminName
     * @return
     */
    @Override
    public boolean checkShopAdminNameDuplicate(String shopAdminName) {
//      根据数据库中查询的商家管理员用户名数量进行判断，如果大于零，说明数据库中已有此值
        int resultNum = businessDao.isShopAdminNameDuplicate(shopAdminName);
        if(resultNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 商家注册
     * @param request
     */
    @Override
    public int shopRegister(HttpServletRequest request) {
        String shopAdminName = request.getParameter("username");
        String shopAdminPassword = request.getParameter("password");
        String shopName = request.getParameter("shopName");
        try {
            shopName = URLEncoder.encode(shopName, "UTF-8");
            shopName = URLDecoder.decode(shopName,"UTF-8");
            shopName = URLDecoder.decode(shopName,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Business business = new Business();
        business.setShopAdminName(shopAdminName);
        business.setShopAdminPassword(shopAdminPassword);
        business.setShopName(shopName);
//      获取当前系统时间
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        business.setShopRegisterTime(sdf.format(currentTime));
//      将商品参数保存早到数据库中
        return businessDao.shopRegister(business);
    }

    /**
     * 商家管理员登录
     * @param request
     * @return
     */
    @Override
    public boolean shopAdminLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int resultNum = businessDao.shopAdminLogin(username, password);
        if(resultNum == 1){
            return true;
        } else {
            return false;
        }
    }


}
