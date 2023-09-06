package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Address;
import com.example.mycomputerstore.service.impl.IAddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同一块打包
//RunWith:表示启动这个单元测试类，需要传递一个参数，必须是SpringBoot的实例对象
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressServiceImpl addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("12121");
        address.setName("女");
        addressService.addAddress(23,"管理员",address);
    }
}
