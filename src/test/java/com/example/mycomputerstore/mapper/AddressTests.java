package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(5);
        address.setPhone("15215498897");
        address.setName("ZLY");
        addressMapper.insertAddress(address);
    }

    @Test
    public void countAddress() {
        Integer num = addressMapper.countAddress(5);
        System.out.println(num);
    }
}