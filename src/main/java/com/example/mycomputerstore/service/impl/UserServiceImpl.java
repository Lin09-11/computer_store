package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.entity.User;
import com.example.mycomputerstore.mapper.UserMapper;
import com.example.mycomputerstore.service.IUserService;
import com.example.mycomputerstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Dictionary;
import java.util.UUID;

/**
 * 用户模块业务层实现类
 */
@Service//将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username) 判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //判断结果集是否为null
        if(result!=null){
            //如果不为null则抛出用户名被占用的异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理的实现：md5算法的形式：57hwdowhdow
        //串【盐值】+passsword+串【盐值】----md5算法进行加密，连续加载三次
        //盐值：随机的字符串
        String oldpassword = user.getPassword();
        //获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理,忽略原有密码强度提升了数据安全性
        String md5Password = getMD5Password(oldpassword, salt);
        //将加密后的密码重新补全设置到user中
        user.setPassword(md5Password);

        //补全数据：is_delete设置为0
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        //执行插入成功rows=1
        if(rows!=1){
            throw new InsertException("用户注册过程产生了未知的异常");
        }
    }

    /**
     * 定义一个md5算法的加密处理
     */
    private String getMD5Password(String password, String salt) {
        //MD5加密算法的调用(进行三次加密）
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //返回之后的密码
        return password;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        //根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否正确
        //1.先获取数据库中的加密之后的密码
        String oldPassword = result.getPassword();
        //2.和用户的传递过来的密码进行比较
        //2.1 先获取盐值，上一次在注册时候所自动生成的盐值
        String salt = result.getSalt();
        //2.2 将用户的密码按照相同的md5算法的规则进行加密
        String newMd5Password = getMD5Password(password, salt);
        //3.将密码进行比较
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断is_delete字段的值是否为1表示标记为删除
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //将当前用户的数据返回，返回的数据是为了辅助其他页面做数据展示
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     * 修改者和修改时间可以直接在实现类中进行new
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        //通过uid查找用户是否存在
        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据未找到");
        }
        //原始密码和数据库中密码进行比较
        String inputOldPwd = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(inputOldPwd)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新密码设置到数据库中，将新的密码进行加密，再去更新
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UserNotFoundException("更新数据产生未知的异常");
        }
    }

    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return
     */
    @Override
    public User getByUid(Integer uid) {
        //判断用户是否存在
        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        /**
         * 将数据直接传递给前端
         */
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        return user;
    }

    /**
     * 更新用户的数据操作【SpringBoot实现依赖注入时，自动提交表单的数据，所以我们手动将用户的uid和username加入到user中】
     * User中的数据phone email gender 手动将uid,username进行封装
     * @param uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        //判断用户是否存在
        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        //潜在参数
        user.setModifiedTime(new Date());
        user.setModifiedUser(username);
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 用户头像的路径
     * @param username 用户名称
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //查询当前用户的数据是否存在
        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新用户头像产生未知的异常");
        }

    }
}
