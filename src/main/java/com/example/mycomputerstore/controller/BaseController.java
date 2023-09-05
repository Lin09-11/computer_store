package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.service.ex.*;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;


    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //ServiceException.class：表示抛出这个异常才会调用这个方法
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生异常，被统一拦截到此方法中，这个方法此时就充当是请求处理方法，方法的返回值直接给到前端

    @ExceptionHandler(ServiceException.class)//统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户名的密码错误异常");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if(e instanceof UpdateException){
            result.setState(5003);
            result.setMessage("更新数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 使用session来保存用户的uid和username，因为我们要在每一个页面进行展示
     * 所以我们直接在写BaseController，因为BaseController会被所有的Controller继承
     */
    /**
     * 在登录的方法中将数据封装在session对象中。服务本身自动创建有session对象，已经是一个
     * 全局的session对象。SpringBoot直接使用session对象，直接将HttpSession类型的对象作为请求处理方法的参数
     * 会自动将全局的session对象注入到请求处理方法的session形参上
     */
    /**
     * 获取session对象中的uid
     * @param session：session对象
     * @return 当前用户登录的用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session：session对象
     * @return 当前用户登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}