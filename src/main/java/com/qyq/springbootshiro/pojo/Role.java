package com.qyq.springbootshiro.pojo;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private int rid;
    private String rname;
    private List<User> users;
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private List<Premission> premissions;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Premission> getPremissions() {
        return premissions;
    }

    public void setPremissions(List<Premission> premissions) {
        this.premissions = premissions;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
