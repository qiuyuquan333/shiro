package com.qyq.springbootshiro.mapper;

import com.qyq.springbootshiro.pojo.Student;

public interface StudentMapper {
    /**
     * 根据sname查询student角色权限
     * @param sname
     * @return
     */
    public Student findStudentRPbyName(String sname);

    /**
     * 根据sname查询student
     * @param sname
     * @return
     */
    public Student findStudentByname(String sname);
}
