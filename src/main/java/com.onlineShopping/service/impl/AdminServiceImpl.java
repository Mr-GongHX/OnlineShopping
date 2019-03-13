package com.onlineShopping.service.impl;

import com.onlineShopping.dao.AdminDao;
import com.onlineShopping.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员实现类
 * @Author Gong HaoXin
 * @Date 2019-02-12 19:16:40
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    /**
     * 检测管理员登录业务
     * @param username
     * @param password
     * @return admin
     */
    @Override
    public boolean checkAdminLogin(String username, String password) {
        if(adminDao.checkAdminLogin(username, password) == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查询平台详细信息
     * @return
     */
    @Override
    public Map<String, Object> showPlatformDetail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "totalTrade", adminDao.showPlatformTotalTrade());
        map.put("totalUsers", adminDao.showPlatformTotalUsers());
        map.put("totalBusinesses", adminDao.showPlatformTotalBusiness());
        map.put("totalGoods", adminDao.showPlatformTotalGoods());
        return map;
    }

    /**
     * 商品审核
     * @return
     */
    @Override
    public List<Map<String, Object>> goodsCheck() {
        return adminDao.goodsCheck();
    }

    /**
     * 更新商品审核状态
     * @param request
     * @return
     */
    @Override
    public boolean updateGoodsStatus(HttpServletRequest request) {
        String goodsId = request.getParameter("goodsId");
//      商品通过审核
        if(request.getParameter("action").equals("pass")){
            if(adminDao.goodsPass(goodsId) == 1){
                return true;
            } else {
                return false;
            }
//      商品审核不通过
        } else if (request.getParameter("action").equals("refuse")) {
            if(adminDao.goodsRefuse(goodsId) == 1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 评论审核
     * @return
     */
    @Override
    public List<Map<String, Object>> commentCheck() {
        return adminDao.commentCheck();
    }

    /**
     * 更新评论审核状态
     * @param request
     * @return
     */
    @Override
    public boolean updateCommentStatus(HttpServletRequest request) {
        String commentId = request.getParameter("commentId");
//      评论通过审核
        if(request.getParameter("action").equals("pass")){
            if(adminDao.commentPass(commentId) == 1){
                return true;
            } else {
                return false;
            }
//      评论审核不通过
        } else if (request.getParameter("action").equals("refuse")) {
            if(adminDao.commentRefuse(commentId) == 1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
