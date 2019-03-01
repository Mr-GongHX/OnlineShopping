package com.onlineShopping.controller;

import com.onlineShopping.model.Goods;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品Controller
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
     * 根据商品id上传商品展示视频
     * @param model
     * @param goodsId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadGoodsVideo-{goodsId}")
    public String uploadGoodsVideo(Model model, @PathVariable String goodsId, HttpServletRequest request) {
        model.addAttribute("goodsId", goodsId);
//      根据商品id上传商品展示视频
        goodsService.uploadVideo(request, goodsId);
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
//      设置消息头
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
//      从数据库中查询图片路径
        String imgUrl = goodsService.showMyGoodsImgByGoodsId(imgType, Integer.parseInt(goodsId));
        System.out.println("图片:" + imgUrl);
        File file = null;
        FileInputStream fis = null;
//      分割数据库中保存图片路径的字符串
        List<String> result = Arrays.asList(imgUrl.split(","));
        for (String imgPath :  result) {
            System.out.println("图片URL："+imgPath);
            if(imgPath != null && !imgPath.equals("")) {
                file = new File(imgPath);
                try {
                    fis = new FileInputStream(file);
                    long size = file.length();
                    byte[] temp = new byte[(int) size];
                    fis.read(temp, 0, (int) size);
                    fis.close();
                    OutputStream out = response.getOutputStream();
                    out.write(temp);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return "Image Not Found";
            }
        }
        return "COMPLETE";
    }

    /**
     * 根据商品id展示商品展示视频
     * @param response
     */
    @ResponseBody
    @RequestMapping("/showMyGoodsVideo-{goodsId}")
    public String showMyGoodsVideoByGoodsId(Model model, @PathVariable String goodsId, HttpServletResponse response){
        model.addAttribute("goodsId", goodsId);
        return goodsService.showMyGoodsVideoByGoodsId(response, Integer.parseInt(goodsId));
    }

    /**
     * 根据商品id查找商品信息
     * @param model
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/showGoods-{goodsId}")
    public Goods showGoodsByGoodsId(Model model, @PathVariable String goodsId){
        model.addAttribute("goodsId", goodsId);
        System.out.println("进入商品查找：");
        System.out.println("结果："+goodsService.showGoodsByGoodsId(Integer.parseInt(goodsId)));
        return goodsService.showGoodsByGoodsId(Integer.parseInt(goodsId));
    }

    /**
     * 根据操作类型（修改商品，删除商品）以及商品id对其进行操作
     * @param request
     * @param model
     * @param operateType
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/operateGoods-{operateType}-{goodsId}")
    public String operateGoods(HttpServletRequest request, Model model, @PathVariable String operateType, @PathVariable String goodsId ){
        model.addAttribute("operateType", operateType);
        model.addAttribute("goodsId", goodsId);
        System.out.println("进入修改商品");
        return goodsService.operateGoods(request, operateType, goodsId);
    }

}

