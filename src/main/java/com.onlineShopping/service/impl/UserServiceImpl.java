package com.onlineShopping.service.impl;

import com.onlineShopping.dao.UserDao;
import com.onlineShopping.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户业务实现类
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-12 19:25:50
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 根据关键字搜索商品
     * @param searchName
     * @return
     */
    @Override
    public List<Map<String, Object>> searchGoods(String searchName) {
        return userDao.searchGoods(searchName);
    }

    /**
     * 展示手机专区商品
     * @return
     */
    @Override
    public List<Map<String, Object>> showPhoneAreaGoods() {
        return userDao.showPhoneAreaGoods();
    }

    /**
     * 展示电脑专区商品
     * @return
     */
    @Override
    public List<Map<String, Object>> showComputerAreaGoods() {
        return userDao.showComputerAreaGoods();
    }

    /**
     * 展示外设专区商品
     * @return
     */
    @Override
    public List<Map<String, Object>> showAccessoryAreaGoods() {
        return userDao.showAccessoryAreaGoods();
    }

    /**
     * 低价好货商品
     * @return
     */
    @Override
    public List<Map<String, Object>> showLowPriceGoods() {
        return userDao.showLowPriceGoods();
    }

    /**
     * 新上架商品
     * @return
     */
    @Override
    public List<Map<String, Object>> showNewGoods() {
        return userDao.showNewGoods();
    }

    /**
     * 根据商品id查找商品详情
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> goodsInfo(HttpServletRequest request) {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        return userDao.goodsInfo(goodsId);
    }

    /**
     * 根据商品id查找商品评论详情
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> goodsCommentDetail(HttpServletRequest request) {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        return userDao.goodsCommentDetail(goodsId);
    }


}
