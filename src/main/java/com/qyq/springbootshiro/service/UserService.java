package com.qyq.springbootshiro.service;

import com.qyq.springbootshiro.pojo.User;

public interface UserService {
    public User findUserByname(String uname);

    public void registerUser(String uname, String upassword);

    public void registerStudent(String sname,String spassword);
}
