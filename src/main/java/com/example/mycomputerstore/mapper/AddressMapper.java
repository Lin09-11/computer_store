package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Address;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

//收货地址持久层接口
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     *
     * @param address 收货地址
     * @return 受影响的行数
     */
    Integer insertAddress(Address address);

    /**
     * 获取收货地址的数量（不能超过20），根据用户的uid
     *
     * @param uid 用户uid
     * @return 收货地址数量
     */
    Integer countAddress(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer id);

    /**
     * 根据aid查询收货地址数据
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid值来修改用户的收货地址设置为非默认
     * @param uid
     * @return 受到影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 将用户当前选中的aid【正在填写的地址】设置为默认地址
     * 实际上，上面的findByAid和updateNonDefault是被updateDefaultByAid所调用的
     *
     * @param aid
     * @return
     */
    Integer updateDefaultByAid(
            @Value("aid") Integer aid,
            @Value("modifiedUser") String modifiedUser,
            @Value("modifiedTime") Date modifiedTime);


    /**
     * 根据收货地址id删除收货地址数据
     *
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询当前用户最后一次被修改的收货地址数据
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);

}



