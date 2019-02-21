package com.onlineShopping.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品业务逻辑接口
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-17 15:03:35
 **/
public interface GoodsService {

//  上传商品参数
    public String uploadGoodsInfo(HttpServletRequest request);

//  根据商品id上传对应图片
    public void uploadImg(HttpServletRequest request, String imgType, String goodsId);

//  根据商家id查找所有商品
    public List<Map<String, Object>> showMyGoodsByShopId(Integer shopId);

//  根据商品图片类型（商品展示图，商品详情图）以及商品id查找图片
    public String showMyGoodsImgByGoodsId(String imgType, Integer goodsId);


}
