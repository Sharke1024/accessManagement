package com.itheima.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 日志查询表现层
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true) Integer page,@RequestParam(name = "pageSize",required = true) Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<SysLog> sysLogs = sysLogService.findAll(page,pageSize);
        //pageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("sysLogs",sysLogs);

        mv.setViewName("syslog-list");
        return mv;
    }

}
