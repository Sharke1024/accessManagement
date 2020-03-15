package com.itheima.ssm.dao;

import com.itheima.ssm.ssm.Role;
import com.itheima.ssm.ssm.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 */
@Repository
public interface IUserDao {

    /**
     * 用户登陆认证
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findByUserId")),
    })
    UserInfo findUserByUsername(String username) throws  Exception;

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws  Exception;

    /**
     * 添加用户
     */
    @Insert("insert into users (username,email,password,phoneNum,status) values (#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId")),
    })
    UserInfo findById(String userId) throws  Exception;

    /**
     * 根据用户id查询可以添加的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * 给用户添加权限
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
