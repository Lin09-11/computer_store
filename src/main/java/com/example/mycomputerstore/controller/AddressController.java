package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.Address;
import com.example.mycomputerstore.service.IAddressService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户收货地址
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;


    /**
     * 新增用户收货地址
     *
     * @param address
     * @param session：这里使用session 是为了获取用户的uid和username
     * @return
     */
    @PostMapping("/add_new_address")
    public JsonResult<Void> addAddress(Address address, HttpSession session) {
        //先获取用户uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addAddress(uid, username, address);
        return new JsonResult<>(OK);
    }


    /**
     * 根据用户id展示用户已经填写的地址数据
     *
     * @param session
     * @return
     */
    @GetMapping({"", ""})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    //RestFul风格请求
    @PostMapping("/set_default/{aid}")
    public JsonResult<Void> setDefault(
            @PathVariable("aid") Integer aid,
            HttpSession session
    ) {
        addressService.setDefault(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session)
        );
        System.out.println("OK");
        return new JsonResult<>(OK);
    }


    /**
     * 删除用户地址
     *
     * @param session
     * @param aid
     * @return
     */
    @PostMapping("/delete/{aid}")
    public JsonResult<Void> delete(
            HttpSession session,
            @PathVariable("aid") Integer aid) {
        addressService.delete(aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
