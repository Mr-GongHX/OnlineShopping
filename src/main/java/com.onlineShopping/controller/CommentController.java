package com.onlineShopping.controller;

import com.onlineShopping.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 商品评价Controller
 * @Auther: Gong HaoXin
 * @Date: 2019-04-17 12:55
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 评价商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/commentGoods")
    public boolean commentGoods(HttpServletRequest request) {
        return commentService.commentGoods(request);
    }
}
