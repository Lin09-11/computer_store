package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(2);
        System.out.println(list);
    }

    @Test
    public void findByAid () {
        System.out.println(addressMapper.findByAid(5));
    }

    @Test
    public void updateNonDefault () {
        System.out.println(addressMapper.updateNonDefault(5));
    }

    @Test
    public void updateDefaultByAid () {
        addressMapper.updateDefaultByAid(1,"管理员",new Date());
    }

    @Test
    public void deleteByUid(){
        addressMapper.deleteByAid(4);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(7));
    }
}
