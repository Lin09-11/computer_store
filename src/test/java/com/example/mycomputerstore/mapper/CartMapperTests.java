package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setPrice(1000L);
        cart.setUid(2);
        cart.setPid(10000022);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,2,"张三",new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart car = cartMapper.findByUidAndPid(1, 10000022);
        System.out.println(car);
    }

    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findVOByUid(2));
    }

    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(2));
    }
}
