package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员
 */
@Repository
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;

}
