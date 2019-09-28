package com.qyq.springbootshiro.shiro;

import com.qyq.springbootshiro.mapper.StudentMapper;
import com.qyq.springbootshiro.pojo.Premission;
import com.qyq.springbootshiro.pojo.Role;
import com.qyq.springbootshiro.pojo.Student;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRealm extends AuthorizingRealm {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Student s = (Student) principalCollection.getPrimaryPrincipal();
        String sname = s.getSname();
        Student student = studentMapper.findStudentRPbyName(sname);
        for (Role roles : student.getRoles()){
            authorizationInfo.addRole(roles.getRname());
            for(Premission premissions : roles.getPremissions()){
                authorizationInfo.addStringPermission(premissions.getPname());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(!supports(token)){
            return null;

        }
        String sname = (String) token.getPrincipal();
        Student student = studentMapper.findStudentByname(sname);
        if(student == null){
            throw new UnknownAccountException("没有此用户！");
        }
        String salt = student.getSid()+sname;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(student,student.getSpassword(), ByteSource.Util.bytes(salt),this.getName());
        return info;
    }


    public boolean supports(UsernamePasswordToken token) {
        return token != null &&    getAuthenticationTokenClass().isAssignableFrom(token.getClass()) && token.getHost().equals("test");
    }
}
