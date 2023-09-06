package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Address;

import java.util.List;

//收货地址业务层接口
public interface IAddressService {
    /**
     * 新增地址信息
     * @param uid 用户uid
     * @param username 作为修改者信息
     * @param address 地址类信息
     */
    void addAddress(Integer uid, String username, Address address);


    /**
     * 获取uid对应用户的所有地址信息
     * @param uid 用户uid
     * @return 地址信息list
     */
    List<Address> getByUid(Integer uid);
}
