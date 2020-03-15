package com.itheima.ssm.dao;

import com.itheima.ssm.ssm.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限dao层
 */
@Repository
public interface IPermissionDao {

    /**
     * 通过角色的id 中间表role_permission 查询权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in(select permissionId from role_permission where roleId =#{roleId})")
    List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     */
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
