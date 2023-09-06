package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.entity.Address;
import com.example.mycomputerstore.mapper.AddressMapper;
import com.example.mycomputerstore.service.IAddressService;
import com.example.mycomputerstore.service.IDistrictService;
import com.example.mycomputerstore.service.ex.AddressCountLimitException;
import com.example.mycomputerstore.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;


    //在添加用户的收货地址的业务层依赖于DistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer MaxAddress = 20;


    @Override
    public void addAddress(Integer uid, String username, Address address) {
        //查询收货地址信息是否大于20
        Integer count = addressMapper.countAddress(uid);
        if(count >= MaxAddress) {
            throw new AddressCountLimitException("收货地址不能超过20条");
        }

        //设置信息
        address.setUid(uid);
        Integer isDelete = count == 0 ? 1 : 0;
        address.setIsDefault(isDelete);
        address.setCreatedTime(new Date());
        address.setCreatedUser(username);
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);

        //对address对象中的数据进行补全:省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        //插入收货地址
        Integer row = addressMapper.insertAddress(address);
        if(row != 1) {
            throw new InsertException("未知错误 在 新建收货地址");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return null;
    }
}
