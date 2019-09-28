package com.qyq.springbootshiro.mapper;

import com.qyq.springbootshiro.pojo.Premission;
import com.qyq.springbootshiro.pojo.Role;
import com.qyq.springbootshiro.pojo.Student;
import com.qyq.springbootshiro.pojo.User;

public interface UserMapper {
    public User findUserByname(String uname);

    public User findByname(String name);

    public int insertUser(User user);

    public Role findRoleById(int rid);

    public int insertUser_Role(int uid, int rid);

    public Premission findPremissionById(int pid);

    public int insertStudent(Student student);

}
