package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import com.itheima.ssm.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切面类处理日志
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz;   //访问的类
    private Method method;  //访问的方法

    /**
     * 前置通知
     *      主要获取开始执行时间，执行的类是哪一个，执行的是哪一个方法
     */
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();  //开始的时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName();    //获取访问的方法的名称

        //获取具体执行方法的Method对象
        Object[] args = jp.getArgs();   //获取访问方法的参数
        if (args == null || args.length ==0 ){
            method = clazz.getMethod(methodName);  //只能获取无参数的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }

    /**
     * 后置通知
     */
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        long time= new Date().getTime() - visitTime.getTime();  //访问时长

        //获取url
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class){
            //1.获取类上的RequestMapping("/order)注解的值
            RequestMapping clazzAnnotation= (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();
                //2.获取方法上的RequestMapping("/findAll.do)注解的值
                RequestMapping  methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];


                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取操作者
                    //通过SecurityContext获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    //也可以通过
//        Object context1 = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //封装SysLog信息
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setMethod("[类名 ]"+clazz.getName() + "[方法名 ]"+method.getName());
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);

                    //调用SysLogService 将访问信息存入
                    sysLogService.save(sysLog);
                }
            }
        }

    }

}
