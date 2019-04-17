package com.onlineShopping.service.impl;

import com.onlineShopping.dao.CommentDao;
import com.onlineShopping.model.Comment;
import com.onlineShopping.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 商品评价业务逻辑
 * @Auther: Gong HaoXin
 * @Date: 2019-04-17 12:57
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    /**
     * 评价商品
     * @param request
     * @return
     */
    @Override
    public boolean commentGoods(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String goodsId = request.getParameter("goodsId");
        String commentContent = request.getParameter("commentContent");
        System.out.println(userId + ":" + goodsId + ":" + commentContent);
//      获取当前系统时间
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      创建评价实例
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setGoodsId(goodsId);
        comment.setCommentContent(commentContent);
        comment.setCommentTime(sdf.format(currentTime));
//      把结果保存到数据库中
        if(commentDao.commentGoods(comment) == 1) {
            return true;
        } else  {
            return false;
        }
    }
}
