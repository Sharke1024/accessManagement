package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysLogDao {

    /**
     * 将访问信息存入数据库
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有信息
     * @return
     * @throws Exception
     */
    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
