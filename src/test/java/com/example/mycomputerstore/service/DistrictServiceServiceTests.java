package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Address;
import com.example.mycomputerstore.entity.District;
import com.example.mycomputerstore.service.impl.IAddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同一块打包
//RunWith:表示启动这个单元测试类，需要传递一个参数，必须是SpringBoot的实例对象
@RunWith(SpringRunner.class)
public class DistrictServiceServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
   public void getByParent(){
        //86 表示中国，代表省的父代号都是86
        List<District> lis = districtService.getByParent("86");
        for (District d :lis){
            System.out.println(lis);
        }
    }
}
