package com.onlineShopping.service.impl;

import com.onlineShopping.dao.BusinessDao;
import com.onlineShopping.model.Business;
import com.onlineShopping.service.BusinessService;
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
 * @Description: 商家实业务逻辑
 * @Author: Gong HaoXin
 * @CreateTime: 2019-01-12 19:25:50
 **/
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    /**
     * 检测商家管理员用户名是否重复
     * @param shopAdminName
     * @return
     */
    @Override
    public boolean checkShopAdminNameDuplicate(String shopAdminName) {
//      根据数据库中查询的商家管理员用户名数量进行判断，如果大于零，说明数据库中已有此值
        int resultNum = businessDao.isShopAdminNameDuplicate(shopAdminName);
        if(resultNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 商家注册
     * @param request
     */
    @Override
    public int shopRegister(HttpServletRequest request) {
        String shopAdminName = request.getParameter("username");
        String shopAdminPassword = request.getParameter("password");
        String shopName = request.getParameter("shopName");
        try {
            shopName = URLEncoder.encode(shopName, "UTF-8");
            shopName = URLDecoder.decode(shopName,"UTF-8");
            shopName = URLDecoder.decode(shopName,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Business business = new Business();
        business.setShopAdminName(shopAdminName);
        business.setShopAdminPassword(shopAdminPassword);
        business.setShopName(shopName);
//      获取当前系统时间
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        business.setShopRegisterTime(sdf.format(currentTime));
//      将商品参数保存早到数据库中
        return businessDao.shopRegister(business);
    }

    /**
     * 商家管理员登录
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> shopAdminLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return businessDao.shopAdminLogin(username, password);
    }

    /**
     * 商家上传头像
     * @param request
     * @param shopId
     * @return
     */
    @Override
    public boolean uploadShopProfile(HttpServletRequest request, String shopId) {
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
        //      存放图片的目录(系统桌面目录 + Project + 图片类型对应的目录)
        String localPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + systemSeparator + "Project" + systemSeparator + "shopAdminProfile";
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
        int resultNum = businessDao.uploadShopProfile(destPath, shopId);
        if(resultNum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据商家id返回商家头像
     * @param response
     * @param shopId
     * @return
     */
    @Override
    public String showShopAdminProfile(HttpServletResponse response, Integer shopId) {
//      返回商家头像的路径
        String imgUrl = businessDao.showShopAdminProfile(shopId);
//      设置消息头
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        File file = null;
        FileInputStream fis = null;
        if(imgUrl != null){
            try {
                file = new File(imgUrl);
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
            return "SUCCESS";
        } else {
            return "Image Not Found";
        }
    }

    /**
     * 根据商家id返回商家信息
     * @param shopId
     * @return
     */
    @Override
    public Map<String, Object> shopInfo(Integer shopId) {
        return businessDao.shopInfo(shopId);
    }

    /**
     * 商家退出登录
     * @param request
     * @return
     */
    @Override
    public boolean shopAdminLogout(HttpServletRequest request) {
        String shopId = request.getParameter("shopId");
        if(shopId != "") {
            return true;
        } else {
            return false;
        }
    }


}
