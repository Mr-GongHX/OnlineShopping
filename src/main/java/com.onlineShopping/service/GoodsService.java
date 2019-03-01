package com.onlineShopping.service;

import com.onlineShopping.model.Goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品业务逻辑接口
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-17 15:03:35
 **/
public interface GoodsService {

//  上传商品参数
    String uploadGoodsInfo(HttpServletRequest request);

//  根据商品id上传对应图片
    void uploadImg(HttpServletRequest request, String imgType, String goodsId);

//  根据商品id上传商品展示视频
    void uploadVideo(HttpServletRequest request, String goodsId);

//  根据商家id查找所有商品
    List<Map<String, Object>> showMyGoodsByShopId(Integer shopId);

//  根据商品图片类型（商品展示图，商品详情图）以及商品id查找图片
    String showMyGoodsImgByGoodsId(String imgType, Integer goodsId);

//  根据商品id查找商品展示视频
    String showMyGoodsVideoByGoodsId(HttpServletResponse response, Integer goodsId);

//  根据商品id查找商品信息
    Goods showGoodsByGoodsId(Integer goodsId);

//  根据操作类型修改商品
    String operateGoods(HttpServletRequest request, String operateType, String goodsId);

}
