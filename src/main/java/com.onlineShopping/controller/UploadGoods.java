package com.onlineShopping.controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 上传商品类
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-12 19:25:50
 **/
@Controller
@RequestMapping("/uploadGoods")
public class UploadGoods {

    /**
     * 上传商品参数
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsInfo")
    public String uploadGoodsInfo(HttpServletRequest request) {
        String shopId = request.getParameter("shopId");
        String typeId = request.getParameter("typeId");
        String goodsPrice = request.getParameter("goodsPrice");
        String goodsAmount = request.getParameter("goodsAmount");
        String goodsname = request.getParameter("goodsname");
        try {
            goodsname =  URLEncoder.encode(goodsname, "UTF-8");
            goodsname = URLDecoder.decode(goodsname,"UTF-8");
            goodsname = URLDecoder.decode(goodsname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(shopId + "," + typeId + "," + goodsname + "," + goodsPrice + "," + goodsAmount);
        return "success";
    }

    /**
     * 上传商品展示图
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsImg")
    public String uploadGoodsImg(HttpServletRequest request) {
        System.out.println("进入上传展示图片");

        try {
            request.setCharacterEncoding("UTF-8");  //设置编码
        } catch (Exception e) {
            e.printStackTrace();
        }

//      获取系统分隔符(UNIX,Linux: "/" , Windows: "\")
        String systemSeparator = File.separator;

//      存放图片的目录(系统桌面目录 + Project + 图片类型对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + "goodsImg";

//      获取从前台传过来得图片
        File dir = new File(localPath);
        if (!dir.exists()) {
            dir.mkdir();
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
                //获取表单的属性名字
                String name = item.getFieldName();
//                System.out.println("我是：" + name);// woshiuserId / woshifileData 没一个属性都是一个单独的对象存在。
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                } else {
                    picture = item;
                    String img = item.getString();
                }
            }


//          设置获取的参数编码
//            String shopId = request.getAttribute("shopId").toString();
//            String typeId = request.getAttribute("typeId").toString();
//            String goodsPrice = request.getAttribute("goodsPrice").toString();
//            String goodsAmount = request.getAttribute("goodsAmount").toString();
//            String goodsname = request.getAttribute("goodsname").toString();
//            goodsname =  URLEncoder.encode(goodsname, "UTF-8");
//            goodsname = URLDecoder.decode(goodsname,"UTF-8");
//            goodsname = URLDecoder.decode(goodsname,"UTF-8");
//            System.out.println(shopId + "," + typeId + "," + goodsname + "," + goodsPrice + "," + goodsAmount);

//          随机生成图片名称
            String imgName = UUID.randomUUID().toString() + ".jpeg";

//          使用IO流，给文件命名，指定存放目标文件地址
            String destPath = localPath + systemSeparator + imgName;

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
            in.close();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    /**
     * 上传商品详情图
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsImgInfo")
    public String uploadGoodsImgInfo(HttpServletRequest request) {
        System.out.println("进入上传详情图片");

        try {
            request.setCharacterEncoding("UTF-8");  //设置编码
        } catch (Exception e) {
            e.printStackTrace();
        }

//      获取系统分隔符(UNIX,Linux: "/" , Windows: "\")
        String systemSeparator = File.separator;

//      存放图片的目录(系统桌面目录 + Project + 图片类型对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + "goodsImgInfo";

//      获取从前台传过来得图片
        File dir = new File(localPath);
        if (!dir.exists()) {
            dir.mkdir();
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
                //获取表单的属性名字
                String name = item.getFieldName();
//                System.out.println("我是：" + name);// woshiuserId / woshifileData 没一个属性都是一个单独的对象存在。
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                } else {
                    picture = item;
                    String img = item.getString();
                }
            }


//          设置获取的参数编码
//            String shopId = request.getAttribute("shopId").toString();
//            String typeId = request.getAttribute("typeId").toString();
//            String goodsPrice = request.getAttribute("goodsPrice").toString();
//            String goodsAmount = request.getAttribute("goodsAmount").toString();
//            String goodsname = request.getAttribute("goodsname").toString();
//            goodsname =  URLEncoder.encode(goodsname, "UTF-8");
//            goodsname = URLDecoder.decode(goodsname,"UTF-8");
//            goodsname = URLDecoder.decode(goodsname,"UTF-8");
//            System.out.println(shopId + "," + typeId + "," + goodsname + "," + goodsPrice + "," + goodsAmount);

//          随机生成图片名称
            String imgName = UUID.randomUUID().toString() + ".jpeg";

//          使用IO流，给文件命名，指定存放目标文件地址
            String destPath = localPath + systemSeparator + imgName;

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
            in.close();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }


    /**
     * 保存图片
     * @param picture
     * @param type goodsImg:商品展示图,goodsImgInfo:商品详情图，userProfile:用户头像
     */
    public void saveImage(FileItem[] picture, String type) {
//      获取系统分隔符(UNIX,Linux: "/" , Windows: "\")
        String systemSeparator = File.separator;

        String imgType = "";

//      根据不同的图片类型放入对应的文件目录
        switch (type){
            case "goodsImg" : imgType = "goodsImg"; break;
            case "goodsImgInfo" : imgType = "goodsImgInfo"; break;
            case "userProfile" : imgType = "userProfile"; break;
        }

//      存放图片的目录(系统桌面目录 + Project + 图片类型对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + imgType;

//      获取从前台传过来得图片
        File dir = new File(localPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

//      创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(dir);
        factory.setSizeThreshold(1024 * 1024);

//      使用DiskFileItemFactory 对象创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);

//      随机生成图片名称
        String imgName = UUID.randomUUID().toString() + ".jpg";

//      使用IO流，给文件命名，指定存放目标文件地址
        String destPath = localPath + systemSeparator + imgName;

//      真正写到磁盘上
        File file = new File(destPath);
        OutputStream out = null;
        byte[] buf = null;
        try {
            out = new FileOutputStream(file);
            InputStream in = picture[0].getInputStream();
//            int length = 0;
//            buf = new byte[1024];
////          每次读到的数据存放在buf 数组中
//            while ((length = in.read(buf)) != -1) {
//                //在buf数组中取出数据写到（输出流）磁盘上
//                out.write(buf, 0, length);
//            }
//            in.close();

//          JPEG转码
            Image src = ImageIO.read(file);		//读入文件
            int width = src.getWidth(null);		//获得源图片宽
            int height = src.getHeight(null);	//获得源图片高
            BufferedImage img = new BufferedImage(width/2, height/2, BufferedImage.TYPE_INT_RGB);
            img.getGraphics().drawImage(src, 0, 0, width/2, height/2, null);		//绘制缩小后的图
            out = new FileOutputStream(file);	//输出到文件流
            JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(out);
            jpeg.encode(img);					//JPEG编码
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpeg", bos);		//得到图像
            buf = bos.toByteArray();			//获得文件字节,转成byte[]数组
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
