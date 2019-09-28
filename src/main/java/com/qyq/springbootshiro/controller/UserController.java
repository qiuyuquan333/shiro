package com.qyq.springbootshiro.controller;

import com.qyq.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String LoginController() {
        return "login.html";
    }

    @RequestMapping("/login1")
    public String StuLoginController() {
        return "stulogin.html";
    }

    @RequestMapping("/index")
    public String IndexController() {
        return "index.html";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.GET)
    public String doLoginController(@RequestParam("uname") String name, @RequestParam("upassword") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(name, password,"user");
//        CustomizedToken token = new CustomizedToken(name,password, LoginType.USER.toString());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            //User user = userService.findUserByname(uname);
            //subject.getSession().setAttribute("user",user);
            return "/index";
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "/login";

    }

    @RequestMapping(value = "/stulogin", method = RequestMethod.GET)
    public String doStuLoginController(@RequestParam("uname") String name, @RequestParam("upassword") String password) {
        //CustomizedToken token = new CustomizedToken(name,password, "");
        UsernamePasswordToken token = new UsernamePasswordToken(name, password,"user");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "/index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/login";

    }

    @RequestMapping("/logout")
    @ResponseBody
    public String LogoutController() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "这是logout页面";
    }

    @RequiresRoles("user")
    @RequestMapping("/add")
    @ResponseBody
    public String AddController() {
        return "add";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String UnauthorizedController() {
        return "这是unauthorized页面";
    }

    @RequestMapping("/goregister")
    public String GoregController() {
        return "register.html";
    }


//    @RequestMapping("/register")
//    @ResponseBody
//    public String RegisterController(@RequestParam("uname") String uname, @RequestParam("upassword") String upassword) {
//        userService.registerUser(uname, upassword);
//        return "注册成功";
//    }

    @RequestMapping("/register")
    @ResponseBody
    public String RegisterController(@RequestParam("sname") String sname, @RequestParam("spassword") String spassword) {
       userService.registerStudent(sname,spassword);
        return "注册成功";
    }
}
