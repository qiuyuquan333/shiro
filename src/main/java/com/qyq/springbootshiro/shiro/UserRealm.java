package com.qyq.springbootshiro.shiro;

import com.qyq.springbootshiro.mapper.StudentMapper;
import com.qyq.springbootshiro.mapper.UserMapper;
import com.qyq.springbootshiro.pojo.Premission;
import com.qyq.springbootshiro.pojo.Role;
import com.qyq.springbootshiro.pojo.User;
import com.qyq.springbootshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User u = (User) principalCollection.getPrimaryPrincipal();//获取信息
        User user = userService.findUserByname(u.getUname());
        //获取角色
        for (Role roles : user.getRoles()) {
            System.out.println(roles);
            authorizationInfo.addRole(roles.getRname());  //添加角色
            //获取权限
            for (Premission premissions : roles.getPremissions()) {
                System.out.println(premissions);
                authorizationInfo.addStringPermission(premissions.getPname());  //添加权限
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //强转成UsernamePasswordToken对象

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(!supports(token)){
            return null;

        }
        //获取用户名
        String uname = token.getUsername();
        //连接数据库查询
        User user = userMapper.findByname(uname);
        //User user = userService.findUserByname(uname);
//
//        if (user == null) {
//            throw new UnknownAccountException("没有此用户！");
//        }
        //获取密码
        String upassword = user.getUpassword();
        //设置盐值，ID+用户名
        String salt = user.getUid() + uname;
        //ByteSource.Util.bytes(user.getCredentialsSalt())

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, upassword, ByteSource.Util.bytes(salt), this.getName());

        return info;
    }

    public boolean supports(UsernamePasswordToken token) {
        return token != null && getAuthenticationTokenClass().isAssignableFrom(token.getClass()) && token.getHost().equals("user");
    }

}
