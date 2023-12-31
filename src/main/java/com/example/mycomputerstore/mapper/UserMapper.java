package com.example.mycomputerstore.mapper;


import com.example.mycomputerstore.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
//@Mapper
public interface UserMapper {

    /**
     * 插入用户的数据
     *
     * @param user
     * @return：受到影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户数据
     * @param username
     * @return：如果找到对应的用户则返回这个用户的数据，如果没有则返回null
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid来修改用户的密码
     * @param uid 用户uid
     * @param password 用户输入的新密码
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改数据的时间
     * @return 返回值为受到影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据用户uid查询用户的数据
     * @param uid 用户uid
     * @return 如果找到则返回对象，反之返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user
     * @return 返回受到影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * @param:是将映射文件中的#{}进行换名，如果不一致的时候
     * 根据用户uid值来最高用户的头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    /**
     * 注解@Param("SQL映射文件中#{}占位符的变量名"),解决的问题:
     * 当SQL语句的占位符和映射的接口方法参数名不一致时,需要将某个参数强行注入到某个
     * 占位符变量上时,可以使用@Param这个注解来标注映射的关系
     * */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
