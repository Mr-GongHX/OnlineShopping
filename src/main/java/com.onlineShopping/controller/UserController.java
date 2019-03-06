package com.onlineShopping.controller;

import com.onlineShopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户Controller
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-12 19:25:50
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据关键字搜索商品
     * @param model
     * @param searchName
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchGoods-{searchName}")
    public List<Map<String, Object>> searchGoods(Model model, @PathVariable String searchName){
        model.addAttribute("searchName", searchName);
        return userService.searchGoods(searchName);
    }

    /**
     * 展示手机专区商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/showPhoneAreaGoods")
    public List<Map<String, Object>> showPhoneAreaGoods(){
        return userService.showPhoneAreaGoods();
    }

    /**
     * 展示电脑专区商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/showComputerAreaGoods")
    public List<Map<String, Object>> showComputerAreaGoods(){
        return userService.showComputerAreaGoods();
    }

    /**
     * 展示外设专区商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/showAccessoryAreaGoods")
    public List<Map<String, Object>> showAccessoryAreaGoods(){
        return userService.showAccessoryAreaGoods();
    }

    /**
     * 低价好货商品展示
     * @return
     */
    @ResponseBody
    @RequestMapping("/showLowPriceGoods")
    public List<Map<String, Object>> showLowPriceGoods(){
        return userService.showLowPriceGoods();
    }

    /**
     * 新上架商品展示
     * @return
     */
    @ResponseBody
    @RequestMapping("/showNewGoods")
    public List<Map<String, Object>> showNewGoods(){
        return userService.showNewGoods();
    }

    /**
     * 根据商品id查找商品详情
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsInfo")
    public List<Map<String, Object>> goodsInfo(HttpServletRequest request){
        return userService.goodsInfo(request);
    }

    /**
     * 根据商品id查找商品评论详情
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsCommentDetail")
    public List<Map<String, Object>> goodsCommentDetail(HttpServletRequest request){
        return userService.goodsCommentDetail(request);
    }

    /**************************/


    /**
     * 检测用户名是否重复
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUsernameDuplicate")
    public boolean checkUsernameDuplicate(HttpServletRequest request) {
        String username = request.getParameter("username");
        return userService.checkUsernameDuplicate(username);
    }

    /**
     * 用户注册
     * @param request
     */
    @ResponseBody
    @RequestMapping("/userRegister")
    public boolean shopRegister(HttpServletRequest request) {
//      返回1条结果代表注册成功
        if(userService.userRegister(request) == 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/userLogin")
    public Map<String, Object> shopAdminLogin(HttpServletRequest request) {
        return userService.userLogin(request);
    }

    /**
     * 用户上传头像
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadUserProfile-{userId}")
    public boolean uploadUserProfile(HttpServletRequest request, Model model, @PathVariable String userId){
        model.addAttribute("userId", userId);
        System.out.println("进入用户上传头像");
        return userService.uploadUserProfile(request, userId);
    }

    /**
     * 根据用户id返回用户头像
     * @param response
     * @param model
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/userProfile-{userId}")
    public String showShopAdminProfile(HttpServletResponse response, Model model, @PathVariable String userId) {
        model.addAttribute("shopId", userId);
        return userService.showUserProfile(response, Integer.parseInt(userId));
    }

    /**
     * 根据用户id返回用户信息
     * @param model
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/userInfo-{userId}")
    public Map<String, Object> shopInfo(Model model, @PathVariable String userId){
        model.addAttribute("userId", userId);
        return userService.userInfo(Integer.parseInt(userId));
    }

    /**
     * 商家退出登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/userLogout")
    public boolean shopAdminLogout(HttpServletRequest request){
        return userService.userLogout(request);
    }
}
