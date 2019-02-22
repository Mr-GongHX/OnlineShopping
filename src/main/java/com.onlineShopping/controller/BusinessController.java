package com.onlineShopping.controller;

import com.onlineShopping.service.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 商家Controller
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-22 22:03:32
 **/
@Controller
@RequestMapping("/shop")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * 检测商家管理员用户名是否重复
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkShopAdminNameDuplicate")
    public boolean checkShopAdminNameDuplicate(HttpServletRequest request) {
        String shopAdminName = request.getParameter("username");
        return businessService.checkShopAdminNameDuplicate(shopAdminName);
    }

    /**
     * 商家注册
     * @param request
     */
    @ResponseBody
    @RequestMapping("/shopRegister")
    public boolean hopRegister(HttpServletRequest request) {
//      返回1条结果代表注册成功
        if(businessService.shopRegister(request) == 1){
            return true;
        } else {
            return false;
        }
    }

}
