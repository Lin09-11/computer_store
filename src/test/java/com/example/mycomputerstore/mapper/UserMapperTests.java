package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同一块打包
//RunWith:表示启动这个单元测试类，需要传递一个参数，必须是SpringBoot的实例对象
@RunWith(SpringRunner.class)
public class UserMapperTests {

    //idea检测功能，接口是不能直接创建Bean（动态代理来解决）
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试
     * 1.返回值必须是void
     * 2.必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("张三");
        System.out.println(user);
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(2));
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(2,
                "321","管理员",new Date());

    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(3);
        user.setPhone("123456");
        user.setEmail("yyq");
        user.setGender(1);
        user.setModifiedUser("smy");
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(2,"/upload/avatar.png","管理员",new Date());
    }
}
