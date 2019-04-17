package com.onlineShopping.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 商品评价接口
 * @Auther: Gong HaoXin
 * @Date: 2019-04-17 12:56
 */
public interface CommentService {

//  评价商品
    boolean commentGoods(HttpServletRequest request);
}
