package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.controller.ex.*;
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

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result  = new JsonResult<>(e);

        if(e instanceof UsernameDuplicatedException) {
            result.setState(5000);
            result.setMessage("用户名已存在");
        } else if(e instanceof InsertException) {
            result.setState(5001);
            result.setMessage("注册时未知异常");
        } else if(e instanceof UserNotFoundException) {
            result.setState(5002);
            result.setMessage("该用户不存在");
        } else if(e instanceof PasswordNotMatchException) {
            result.setState(5003);
            result.setMessage("密码错误");
        } else if(e instanceof UpdateException) {
            result.setState(5004);
            result.setMessage("更新数据的未知异常");
        } else if(e instanceof AddressCountLimitException) {
            result.setState(5005);
            result.setMessage("收货地址超出上限");
        } else if(e instanceof AddressNotFoundException) {
            result.setState(5006);
            result.setMessage("用户收货地址未找到");
        } else if(e instanceof AccessDeniedException) {
            result.setState(5007);
            result.setMessage("收货地址数据非法访问");
        } else if(e instanceof UpdateException) {
            result.setState(5008);
            result.setMessage("删除数据的未知异常");
        } else if(e instanceof ProductNotFoundException) {
            result.setState(5009);
            result.setMessage("商品数据不存在");
        } else if(e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("头像文件为空");
        } else if(e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小不匹配");
        } else if(e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型错误");
        } else if(e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态异常");
        } else if(e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("头像文件读取异常");
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
