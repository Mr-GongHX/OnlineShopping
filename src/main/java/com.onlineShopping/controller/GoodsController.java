package com.onlineShopping.controller;

import com.onlineShopping.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description: 上传商品类
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-12 19:25:50
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 上传商品参数并返回goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadGoodsInfo")
    public String uploadGoodsInfo(HttpServletRequest request) {
        System.out.println("进入上传商品参数");
        String returnGoodsId = goodsService.uploadGoodsInfo(request);
        System.out.println("数据："+returnGoodsId);
        return returnGoodsId;
    }

    /**
     * 根据商品id上传商品展示图
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadGoodsImg-{goodsId}")
    public String uploadGoodsImg(Model model, @PathVariable String goodsId, HttpServletRequest request) {
        model.addAttribute("goodsId", goodsId);
        System.out.println("进入上传展示图片");
//      根据商品id上传商品展示图
        goodsService.uploadImg(request, "goodsImg", goodsId);
        return "success";
    }

    /**
     * 根据商品id上传商品详情图
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadGoodsImgInfo-{goodsId}")
    public String uploadGoodsImgInfo(Model model, @PathVariable String goodsId, HttpServletRequest request) {
        model.addAttribute("goodsId", goodsId);
        System.out.println("进入上传详情图片");
//      根据商品id上传商品详情图
        goodsService.uploadImg(request, "goodsImgInfo", goodsId);
        return "success";
    }

    /**
     * 根据商家id查找所有商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/showMyGoods-{shopId}")
    public List<Map<String, Object>> showMyGoods(Model model, @PathVariable String shopId){
        model.addAttribute("shopId", shopId);
        System.out.println("进入我的商品");
        goodsService.showMyGoodsByShopId(Integer.parseInt(shopId));
        return goodsService.showMyGoodsByShopId(Integer.parseInt(shopId));
    }

    /**
     * 根据商品图片类型（商品展示图，商品详情图）以及商品id查找图片
     * @param model
     * @param imgType
     * @param goodsId
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/showMyGoodsImg-{imgType}-{goodsId}")
    public String showMyGoodsImg(Model model, @PathVariable String imgType, @PathVariable String goodsId, HttpServletResponse response){
        model.addAttribute("imgType", imgType);
        model.addAttribute("goodsId", goodsId);
        System.out.println("进入查找图片");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String imgUrl = goodsService.showMyGoodsImgByGoodsId(imgType, Integer.parseInt(goodsId));
        System.out.println("图片:" + imgUrl);
        File file = null;
        FileInputStream fis = null;
        if(imgUrl != null) {
            file = new File(imgUrl);
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                response.setContentType("image/jpeg");
                OutputStream out = response.getOutputStream();
                out.write(temp);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        } else {
            return "Image Not Found";
        }
    }

}

