package com.onlineShopping.dao;


import com.onlineShopping.model.Comment;
import com.onlineShopping.model.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户Dao
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-12 19:25:50
 **/
public interface UserDao {

//  根据关键字搜索商品
    List<Map<String, Object>> searchGoods(String searchName);

//  展示手机专区商品
    List<Map<String, Object>> showPhoneAreaGoods();

//  展示电脑专区商品
    List<Map<String, Object>> showComputerAreaGoods();

//  展示外设专区商品
    List<Map<String, Object>> showAccessoryAreaGoods();

//  低价好货商品
    List<Map<String, Object>> showLowPriceGoods();

//  新上架商品
    List<Map<String, Object>> showNewGoods();

//  根据商品id查找商品详情
    List<Map<String, Object>> goodsInfo(Integer goodsId);

//  根据商品id查找商品评论详情
    List<Map<String, Object>> goodsCommentDetail(Integer goodsId);

//  检测用户名是否重复
    int isUsernameDuplicate(String username);

//  用户注册
    int userRegister(User user);

//  用户登录
    Map<String, Object> userLogin(@Param("username") String username, @Param("password") String password);

//  用户上传头像
    int uploadUserProfile(@Param("userProfile") String userProfile, @Param("userId") String userId);

//  根据用户id返回用户头像
    String showUserProfile(int userId);

//  根据用户id返回用户信息
    Map<String, Object> userInfo(int userId);

//  修改用户余额
    int updateUserBalance(@Param("userId") String userId, @Param("orderPrice") Double orderPrice);

//  商品评价
    int goodsComment(Comment comment);
}
