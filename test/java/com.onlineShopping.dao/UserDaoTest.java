package com.onlineShopping.dao;

import com.onlineShopping.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */
// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testUserDao(){
        User user = userDao.findByUsername("张三");
        System.out.println("查询结果："+user.toString());
    }


}
