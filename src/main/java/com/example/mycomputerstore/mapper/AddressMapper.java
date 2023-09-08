package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Address;

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
}
