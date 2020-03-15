package com.itheima.ssm.service;

import com.itheima.ssm.ssm.SysLog;

import java.util.List;

/**
 * 日志类业务层
 */
public interface ISysLogService {


    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer pageSize) throws Exception;
}
