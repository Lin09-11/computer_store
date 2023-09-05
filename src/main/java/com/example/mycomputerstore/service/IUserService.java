package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.User;

/**
 * 用户模块业务层接口
 */
public interface IUserService {

    /**
     * 用户注册方法
     * @param user
     */
    void reg(User user);

    /**
     * 用户登录
     * 需要传递两个参数，用户名和密码，当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     * 修改者和修改时间可以直接在实现类中进行new
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);



    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     * @param uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    void changeInfo(Integer uid,String username,User user);
}
