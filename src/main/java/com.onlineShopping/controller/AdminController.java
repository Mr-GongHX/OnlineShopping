package com.onlineShopping.controller;

import com.onlineShopping.service.AdminService;
import com.onlineShopping.service.BusinessService;
import com.onlineShopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 在线购物网登录类(管理员，商家，用户)
 * @Author Gong HaoXin
 * @CreateTime 2019-02-12 19:16:40
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 管理员登录
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password =  request.getParameter("password");
        if(adminService.checkAdminLogin(username, password)) {
            try {
                response.sendRedirect("/view/pages/index.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out= null;
            try {
                out = response.getWriter();
                out.print("<script>alert('用户名或密码错误，请重试');history.go(-1);</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 查询平台详情
     * @return
     */
    @ResponseBody
    @RequestMapping("/showPlatformDetail")
    public Map<String, Object> showPlatformDetail() {
        return adminService.showPlatformDetail();
    }

}
