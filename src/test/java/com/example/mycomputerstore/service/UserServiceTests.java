package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.User;
import com.example.mycomputerstore.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同一块打包
//RunWith:表示启动这个单元测试类，需要传递一个参数，必须是SpringBoot的实例对象
@RunWith(SpringRunner.class)
public class UserServiceTests {


    //idea检测功能，接口是不能直接创建Bean（动态代理来解决）
    @Autowired
    private IUserService userService;

    /**
     * 单元测试
     * 1.返回值必须是void
     * 2.必须是public
     */
    @Test
    public void insert(){
        try {
            User user = new User();
            user.setUsername("xyyz");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (SecurityException e){
            //获取类的对象，在获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("test001", "123");
        System.out.println(user);
    }


    @Test
    public void changePassword(){
        userService.changePassword(7,"管理员",
                "123","321");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(2));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("1212121");
        user.setGender(1);
        user.setEmail("whqhiw@");
        userService.changeInfo(2,"管理员",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(2,"/upload/text.png", "管理员");
    }
}
