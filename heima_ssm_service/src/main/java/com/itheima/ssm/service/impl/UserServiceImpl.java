package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户登陆认证实现类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 用户登陆认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo =null;
        try {
             userInfo= userDao.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthorities());
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()  ==0?false:true,true,true,true,getAuthorities(userInfo.getRoles()));
        return user;
    }

    /**
     * 作用就是返回一个list集合，集合中装入角色的描述
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }
        return list;
    }


    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    /**
     * 添加用户
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //加密密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 通过id查询用户
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String userId) throws Exception {

        return userDao.findById(userId);
    }

    /**
     * 根据用户id查询可以添加的角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    /**
     * 给用户添加权限
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
