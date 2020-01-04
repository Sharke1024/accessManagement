package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户表现层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;



    /**
     * 查找所有
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  //只有admin角色才能访问
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='bob'")   //只有bob用户才能添加
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询用户
     * @param UserId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) String UserId)throws  Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(UserId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 查询用户可以添加的角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2.根据用户id查询可以添加的角色
        List<Role> roles =userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

}
