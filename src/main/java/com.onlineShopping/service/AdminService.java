package com.onlineShopping.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 管理员接口
 * @Author Gong HaoXin
 * @Date 2019-02-12 19:16:40
 */
public interface AdminService {

    //检测管理员登录
    boolean checkAdminLogin(String username, String password);

    //查询平台详细信息
    Map<String, Object> showPlatformDetail();

    //商品审核
    List<Map<String, Object>> goodsCheck();

    //更新商品审核状态
    boolean updateGoodsStatus(HttpServletRequest request);

    //评论审核
    List<Map<String, Object>> commentCheck();

    //更新评论审核状态
    boolean updateCommentStatus(HttpServletRequest request);

}
