package com.onlineShopping.controller;

import com.onlineShopping.model.Administrator;
import com.onlineShopping.model.Business;
import com.onlineShopping.model.User;
import com.onlineShopping.service.AdminService;
import com.onlineShopping.service.BusinessService;
import com.onlineShopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在线购物网登录类(管理员，商家，用户)
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */

@Controller
@RequestMapping("/login.do")
public class LoginController {

    @Resource
    private AdminService adminService;
    private BusinessService businessService;
    private UserService userService;

    //管理员登录
    @RequestMapping("?username={username}&password={password}&role=admin")
    public void adminLogin(HttpServletRequest request, HttpServletResponse response,
                      Model model, @PathVariable String username,
                      @PathVariable String password, @PathVariable String role){
        Administrator admin = adminService.checkLogin(username, password);
        System.out.println("管理员登录" + admin.toString());

    }

    //商家登录
    @RequestMapping("?username={username}&password={password}&role=business")
    public void businessLogin(HttpServletRequest request, HttpServletResponse response,
                      Model model, @PathVariable String username,
                      @PathVariable String password, @PathVariable String role){
        Business business = this.businessService.showBusiness(username, password);
        System.out.println("管理员登录" + business.toString());

    }

    //用户登录
    @RequestMapping("?username={username}&password={password}-role=user")
    public void userLogin(HttpServletRequest request, HttpServletResponse response,
                              Model model, @PathVariable String username,
                              @PathVariable String password, @PathVariable String role){
        User user = this.userService.checkLogin(username, password);
        System.out.println("管理员登录" + user.toString());

    }

}
