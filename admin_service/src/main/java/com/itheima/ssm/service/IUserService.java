package com.itheima.ssm.service;

import com.itheima.ssm.ssm.Role;
import com.itheima.ssm.ssm.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户登陆认证
 *      UserDetailsService接口实现认证登陆
 */
public interface IUserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 通过id查找用户
     * @param userId
     * @return
     * @throws Exception
     */
    UserInfo findById(String userId) throws Exception;

    /**
     * 根据用户查询可以添加的角色
     * @param userId
     * @return
     */
    List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * 给用户添加权限
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
