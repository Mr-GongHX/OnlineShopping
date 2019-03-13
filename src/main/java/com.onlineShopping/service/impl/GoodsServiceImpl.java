package com.onlineShopping.service.impl;

import com.onlineShopping.dao.GoodsDao;
import com.onlineShopping.model.Goods;
import com.onlineShopping.service.GoodsService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 商品业务逻辑
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-17 15:09:21
 **/
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    /**
     * 获取微信小程序商家端上传的商品参数
     * @param request
     */
    @Override
    public String uploadGoodsInfo(HttpServletRequest request) {
        //商品参数
        String shopId = request.getParameter("shopId");
        String typeId = request.getParameter("typeId");
        String goodsPrice = request.getParameter("goodsPrice");
        String goodsAmount = request.getParameter("goodsAmount");
        String goodsName = request.getParameter("goodsName");
        try {
            //设置获取参数的编码
            goodsName =  URLEncoder.encode(goodsName, "UTF-8");
            goodsName = URLDecoder.decode(goodsName,"UTF-8");
            goodsName = URLDecoder.decode(goodsName,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Goods goods = new Goods();
//      设置商品对应的参数
        goods.setShopId(shopId);
        goods.setTypeId(typeId);
        goods.setGoodsName(goodsName);
        goods.setGoodsPrice(Double.parseDouble(goodsPrice));
        goods.setGoodsAmount(goodsAmount);
//      获取当前系统时间
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        goods.setGoodsUploadTime(sdf.format(currentTime));
        goods.setGoodsModifyTime(sdf.format(currentTime));
//      将商品参数保存早到数据库中
        int result = goodsDao.insertGoodsInfo(goods);
        System.out.println("成功插入" + result + "条数据");
//      获取刚才插入到数据库中的goodsId
        return goods.getGoodsId();
    }

    /**
     * 获取微信小程序商家端上传的图片，并根据图片类型（商品展示图，商品详情图）放入对应的文件夹中以及
     * 根据商品id将图片路径保存到数据库中
     * @param request HTTP请求对象
     * @param imgType 图片类型（goodsImg:商品展示图，goodsImgInfo:商品详情图，userProfile:用户头像图）
     */
    @Override
    public void uploadImg(HttpServletRequest request, String imgType, String goodsId) {
        System.out.println("进入service");
        System.out.println("商品ID:" + goodsId);
        try {
            //设置编码为UTF-8
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
//      获取系统分隔符(UNIX,Linux: "/" , Windows: "\")
        String systemSeparator = File.separator;
//      声明目标文件存储目录
        String destPath = null;
//      选择文件类型
        switch (imgType){
//          商品展示图
            case "goodsImg" : imgType = "goodsImg"; break;
//          商品详情图
            case "goodsImgInfo" : imgType = "goodsImgInfo"; break;
//          用户头像图
            case "userProfile" : imgType = "userProfile"; break;
        }
//      存放图片的目录(系统桌面目录 + Project + 图片类型对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + imgType;
//      判断是否存在本目录
        File dir = new File(localPath);
//      不存在则创建目录
        if (!dir.exists()) {
            dir.setWritable(true);
            dir.mkdirs();
        }
//      创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(dir);
        factory.setSizeThreshold(1024 * 1024);
//      使用DiskFileItemFactory 对象创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            // 对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件（false是上传文件的类型，true是普通表单类型）
            for (FileItem item : list) {
                //判断获取的信息是否是图片文件
                if (!item.isFormField()) {
                    picture = item;
                }
            }
//          获取原始图片名
            String imgOriginalName = picture.getName();
//          获取图片扩展名
            String imgExtensionName = imgOriginalName.substring(imgOriginalName.lastIndexOf(".")+1);
//          随机生成图片名称
            String imgName = UUID.randomUUID().toString() + "." + imgExtensionName;
//          使用IO流，给文件命名，指定存放目标文件地址
            destPath = localPath + systemSeparator + imgName;
//          真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = null;
            byte[] buf = null;
            out = new FileOutputStream(file);
            InputStream in = picture.getInputStream();
            int length = 0;
            buf = new byte[1024];
//          每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
//          关闭流
            in.close();
            out.close();
//          删除临时文件
            picture.delete();
        }catch (Exception e) {
            e.printStackTrace();
        }
//      将图片路径保存到数据库中
        System.out.println(destPath);
        if(imgType.equals("goodsImg")){
//          根据商品id更新商品展示图
            goodsDao.updateGoodsPicturePath(destPath + ",", goodsId);
        }else if(imgType.equals("goodsImgInfo")){
//          根据商品id更新商品详情图
            goodsDao.updateGoodsPictureInfoPath(destPath + ",", goodsId);
        }
    }

    /**
     * 根据商品id上传商品展示视频
     * @param request
     * @param goodsId
     */
    @Override
    public void uploadVideo(HttpServletRequest request, String goodsId) {
        try {
            //设置编码为UTF-8
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
//      获取系统分隔符(UNIX,Linux: "/" , Windows: "\")
        String systemSeparator = File.separator;
//      声明目标文件存储目录
        String destPath = null;
//      存放视频的目录(系统桌面目录 + Project + 视频文件对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + "goodsVideo";
//      判断是否存在本目录
        File dir = new File(localPath);
//      不存在则创建目录
        if (!dir.exists()) {
            dir.setWritable(true);
            dir.mkdirs();
        }
//      创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(dir);
        factory.setSizeThreshold(1024 * 1024);
//      使用DiskFileItemFactory 对象创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            List<FileItem> list = upload.parseRequest(request);
            FileItem goodsVideo = null;
            // 对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件（false是上传文件的类型，true是普通表单类型）
            for (FileItem item : list) {
                //判断获取的信息是否是视频文件
                if (!item.isFormField()) {
                    goodsVideo = item;
                }
            }
//          获取原始视频名
            String videoOriginalName = goodsVideo.getName();
//          获取视频扩展名
            String videoExtensionName = videoOriginalName.substring(videoOriginalName.lastIndexOf(".")+1);
//          随机生成视频名称
            String videoName = UUID.randomUUID().toString() + "." + videoExtensionName;
//          使用IO流，给文件命名，指定存放目标文件地址
            destPath = localPath + systemSeparator + videoName;
//          真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = null;
            byte[] buf = null;
            out = new FileOutputStream(file);
            InputStream in = goodsVideo.getInputStream();
            int length = 0;
            buf = new byte[1024];
//          每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
//          关闭流
            in.close();
            out.close();
//          删除临时文件
            goodsVideo.delete();
        }catch (Exception e) {
            e.printStackTrace();
        }
//      将视频路径保存到数据库中
        System.out.println(destPath);
        goodsDao.updateGoodsVideo(destPath, goodsId);
    }

    /**
     * 根据商家id查找所有商品
     * @param shopId
     * @return
     */
    @Override
    public List<Map<String, Object>> showMyGoodsByShopId(Integer shopId) {
        System.out.println("结果:"+goodsDao.showMyGoodsByShopId(shopId));
        return goodsDao.showMyGoodsByShopId(shopId);
    }

    /**
     * 根据商品图片类型（商品展示图，商品详情图）以及商品id查找对应图片
     * @param imgType
     * @param goodsId
     * @return
     */
    @Override
    public String showMyGoodsImgByGoodsId(String imgType, Integer goodsId) {
        if(imgType.equals("goodsImg")){
            return goodsDao.showMyGoodsPictureByGoodsId(goodsId);
        } else if(imgType.equals("goodsImgInfo")){
            return goodsDao.showMyGoodsPictureInfoByGoodsId(goodsId);
        } else{
            return "参数错误";
        }
    }

    /**
     * 根据商品id查找商品展示视频
     * @param goodsId
     * @return
     */
    @Override
    public String showMyGoodsVideoByGoodsId(HttpServletResponse response, Integer goodsId) {
        String goodsVideoPath = goodsDao.showMyGoodsVideoByGoodsId(goodsId);
        System.out.println("showMyGoodsVideo:"+goodsVideoPath);
//      若商品展示视频路径不为空，则创建file对象
        if (goodsVideoPath != null && !goodsVideoPath.equals("")){
            File file = new File(goodsVideoPath);
            try{
                FileInputStream inputStream = new FileInputStream(file);
                byte[] data = new byte[(int)file.length()];
                inputStream.read(data);
                inputStream.close();
                response.setContentType("application/octet-stream");
                response.setContentLength(data.length);
                OutputStream stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
                stream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            return "SUCCESS";
        } else {
            return "Video Not Found";
        }
    }

    /**
     * 根据商品id查找商品信息
     * @param goodsId
     * @return
     */
    @Override
    public Goods showGoodsByGoodsId(Integer goodsId) {
        return goodsDao.showGoodsByGoodsId(goodsId);
    }

    /**
     * 根据操作类型修改商品
     * @param request
     * @param operateType
     * @param goodsId
     * @return
     */
    @Override
    public String operateGoods(HttpServletRequest request, String operateType, String goodsId) {
//      获取当前系统时间
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      修改商品
        if(operateType.equals("updateGoods")){
            //商品参数
            String goodsName = request.getParameter("goodsName");
            String goodsPrice = request.getParameter("goodsPrice");
            String goodsAmount = request.getParameter("goodsAmount");
            String typeId = request.getParameter("typeId");
            String goodsStatus = request.getParameter("goodsStatus");
            try {
                //设置获取参数的编码
                goodsName =  URLEncoder.encode(goodsName, "UTF-8");
                goodsName = URLDecoder.decode(goodsName,"UTF-8");
                goodsName = URLDecoder.decode(goodsName,"UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Goods goods = new Goods();
    //      设置商品对应的参数
            goods.setGoodsName(goodsName);
            goods.setGoodsPrice(Double.parseDouble(goodsPrice));
            goods.setGoodsAmount(goodsAmount);
            goods.setTypeId(typeId);
            goods.setGoodsStatus(goodsStatus);
            goods.setGoodsModifyTime(sdf.format(currentTime));
            goodsDao.updateGoodsByGoodsId(goods, goodsId);
            return "修改成功";
//      删除商品
        } else if(operateType.equals("deleteGoods")){
            goodsDao.deleteGoodsByGoodsId(sdf.format(currentTime), goodsId);
            return "删除成功";
        } else {
            return "参数错误";
        }
    }


}
