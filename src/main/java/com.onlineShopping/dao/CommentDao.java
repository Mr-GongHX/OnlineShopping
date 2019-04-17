package com.onlineShopping.dao;

import com.onlineShopping.model.Comment;

/**
 * @Description:  商品评价Dao
 * @Auther: Gong HaoXin
 * @Date: 2019-04-17 13:04
 */
public interface CommentDao {

//  评价商品
    int commentGoods(Comment comment);
}
