package com.itheima.ssm.service;

import com.itheima.ssm.ssm.Permission;
import com.itheima.ssm.ssm.Role;

import java.util.List;

/**
 * 角色业务层
 */
public interface IRoleService {

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    void save(Role role) throws Exception;

    /**
     * 根据角色查询可以添加的权限
     * @return
     */
    List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception;

    /**
     * 根据角色id查询角色
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
