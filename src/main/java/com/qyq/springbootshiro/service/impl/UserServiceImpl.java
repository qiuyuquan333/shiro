package com.qyq.springbootshiro.service.impl;

import com.qyq.springbootshiro.mapper.UserMapper;
import com.qyq.springbootshiro.pojo.Student;
import com.qyq.springbootshiro.pojo.User;
import com.qyq.springbootshiro.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByname(String uname) {
        return userMapper.findUserByname(uname);
    }

    @Override
    public void registerUser(String uname, String upassword) {
        User u = new User();
        u.setUid(4);
        u.setUname(uname);
        String salt = 4 + uname;
        String newUpassword = new SimpleHash("MD5", upassword, ByteSource.Util.bytes(salt), 1).toHex();
        u.setUpassword(newUpassword);
        userMapper.insertUser(u);

    }

    @Override
    public void registerStudent(String sname, String spassword) {
        Student s = new Student();
        s.setSid(1);
        s.setSname(sname);
        s.setSpassword(spassword);
        String salt = 1+sname;
        System.out.println(salt);
        String newSpassword = new SimpleHash("MD5",spassword,ByteSource.Util.bytes(salt), 1).toHex();
        s.setSpassword(newSpassword);
        userMapper.insertStudent(s);
    }

//    @Override
//    public int insertUser() {
//        User u = new User();
//        u.setUid(4);
//        u.setUname("yiyi1");
//        u.setUpassword("123");
//        u.setRole("admin");
//        return userMapper.insertUser(u);
//
//    }
}
