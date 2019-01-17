package com.onlineShopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineShopping.model.User;
import com.onlineShopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

//    @RequestMapping("/showUser.do-{id}")
//    public void showUser(HttpServletRequest request, HttpServletResponse response,
//                         Model model, @PathVariable String id) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        User user = this.userService.showUser(Pattern.compile("[0-9]*").matcher(id).matches() ? Integer.parseInt(id) : 1);
//        ObjectMapper mapper = new ObjectMapper();
//        response.getWriter().write(mapper.writeValueAsString(user));
//        response.getWriter().close();
//    }

}
