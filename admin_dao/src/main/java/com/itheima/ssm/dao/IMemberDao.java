package com.itheima.ssm.dao;

import com.itheima.ssm.ssm.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 会员
 */
@Repository
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;

}
