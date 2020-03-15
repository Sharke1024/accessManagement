package com.itheima.ssm.dao;

import com.itheima.ssm.ssm.Permission;
import com.itheima.ssm.ssm.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色表
 */
@Repository
public interface IRoleDao {

    /**
     * 根据userId通过中间表users_role中id查询role角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId =#{userId})")
    List<Role> findByUserId(String userId) throws Exception;

    /**
     * 查找所有role
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws  Exception;



    /**
     * 存储role
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws  Exception;



    /**
     * 通过userId 关联的中间表users_role 查询role 表中角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in(select roleId from users_role where userId =#{userId})")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "roleName",column = "roleName"),
        @Result(property = "roleDesc",column = "roleDesc"),
        @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询当前角色可以添加的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    /**
     * 根据角色id查询角色
     * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
