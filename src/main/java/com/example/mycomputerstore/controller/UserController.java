package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.User;
import com.example.mycomputerstore.service.IUserService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//@RequestMapping("users")
@RestController//RestController=@RequestMapping+@RequestBody
@RequestMapping("/users")
/**
 * 这里继承BaseController表示：可以处理异常类
 */
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * @GetMapping("/reg") //@RequestBody//表示此方法的响应结果以json格式进行数据的1响应给前端
     * public JsonResult<Void> reg(User user) {
     * //创建响应结果对象
     * JsonResult<Void> result = new JsonResult<>();
     * try {
     * userService.reg(user);
     * result.setState(200);
     * result.setMessage("用户注册成功");
     * } catch (UsernameDuplicatedException e) {
     * result.setState(4000);
     * result.setMessage("用户名被占用");
     * } catch (InsertException e) {
     * result.setState(5000);
     * result.setMessage("注册时产生未知的异常");
     * }
     * return result;
     * }
     */

    /**
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据
     * SpringBoot会将前端url地址中的参数名和pojo类的属性名进行比较，如果这两个名称相同，
     * 则将值注入到pojo类中对于的属性上
     * @param user
     * @return
     */
    @PostMapping("/reg")
    //@RequestBody//表示此方法的响应结果以json格式进行数据的1响应给前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 2.接收数据方式：请求处理的参数列表设置为非pojo类型
     * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较，如果名称
     * 相同则自动完成值的依赖注入
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public JsonResult<User> login(String username,
                                  String password,
                                  HttpSession session){
        User user = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username",user.getUsername());
        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        System.out.println(password);
        return new JsonResult<>(OK,user);

    }

    @PostMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
}
