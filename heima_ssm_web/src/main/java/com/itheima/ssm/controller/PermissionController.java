package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限表现出
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 添加权限
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }


}
