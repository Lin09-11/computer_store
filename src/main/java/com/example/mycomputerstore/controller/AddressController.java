package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.Address;
import com.example.mycomputerstore.service.IAddressService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}
