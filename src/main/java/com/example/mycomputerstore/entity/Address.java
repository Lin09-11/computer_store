package com.example.mycomputerstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收货地址的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    private Integer aid;//收货地址id
    private Integer uid;//归属用户id
    private String name;//收货人姓名
    private String provinceName;//省
    private String provinceCode;//省行政代号
    private String cityName;//市名
    private String cityCode;//市行政代号
    private String areaName;//区名
    private String areaCode;//区行政代号
    private String zip;//邮政编码
    private String address;//详细地址
    private String phone;//手机
    private String tel;//固话
    private String tag;//标签
    private Integer isDefault;//是否默认  0-不默认，1-默认
}
