package com.onlineShopping.dao;

import com.onlineShopping.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商品Dao
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-17 16:41:03
 **/
public interface GoodsDao {

    /**
     * 将商品参数插入到数据库中
     *
     * @param goods
     * @return
     */
    int insertGoodsInfo(Goods goods);

    /**
     * 根据商品id更新商品展示图
     *
     * @param goodsPicturePath
     * @param goodsId
     * @return
     */
    int updateGoodsPicturePath(@Param("goodsPicturePath") String goodsPicturePath, @Param("goodsId") String goodsId);

    /**
     * 根据商品id更新商品详情图
     *
     * @param goodsPictureInfoPath
     * @param goodsId
     * @return
     */
    int updateGoodsPictureInfoPath(@Param("goodsPictureInfoPath") String goodsPictureInfoPath, @Param("goodsId") String goodsId);

    /**
     * 根据商品id更新商品展示视频
     * @param goodsVideoPath
     * @param goodsId
     * @return
     */
    int updateGoodsVideo(@Param("goodsVideoPath") String goodsVideoPath, @Param("goodsId") String goodsId);

    /**
     * 根据商家id查找所有商品
     * @param shopId
     * @return
     */
    List<Map<String, Object>> showMyGoodsByShopId(Integer shopId);

    /**
     * 根据商品id查找商品展示图片
     * @param goodsId
     * @return
     */
    String showMyGoodsPictureByGoodsId(Integer goodsId);

    /**
     * 根据商品id查找商品详情图片
     * @param goodsId
     * @return
     */
    String showMyGoodsPictureInfoByGoodsId(Integer goodsId);

    /**
     * 根据商品id查找商品展示视频
     * @param goodsId
     * @return
     */
    String showMyGoodsVideoByGoodsId(Integer goodsId);

    /**
     * 根据商品id查找商品信息
     * @param goodsId
     * @return
     */
    Goods showGoodsByGoodsId(Integer goodsId);

    /**
     * 根据商品id修改商品信息
     * @param goods
     * @param goodsId
     * @return
     */
    int updateGoodsByGoodsId(@Param("goods") Goods goods, @Param("goodsId") String goodsId);

    /**
     * 根据商品id删除商品(更新张品状态为"已删除")
     * @param goodsModifyTime
     * @param goodsId
     * @return
     */
    int deleteGoodsByGoodsId(@Param("goodsModifyTime") String goodsModifyTime, @Param("goodsId") String goodsId);

}