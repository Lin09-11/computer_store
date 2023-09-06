package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent
     * @return 多个区域信息
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);

}
