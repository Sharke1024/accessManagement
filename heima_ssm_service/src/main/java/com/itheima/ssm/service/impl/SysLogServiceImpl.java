package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.ISysLogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import com.itheima.ssm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 添加日志信息
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    @Override
    public List<SysLog> findAll(Integer page,Integer pageSize) throws Exception {
        //分页查询  参数page是页码值，pageSize是每页显示条数
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll();
    }
}
