package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据用户的父代号查询区域信息
     * @param parent
     */
    //查询的结果可能是多个,所以放在集合中
    List<District> findByParent(String parent);


    String findNameByCode(String code);
}
