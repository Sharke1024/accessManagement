package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

/**
 * 权限业务层接口
 */
public interface IPermissionService {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws  Exception;

    /**
     * 添加权限
     * @param permission
     */
    void save(Permission permission) throws Exception;
}
