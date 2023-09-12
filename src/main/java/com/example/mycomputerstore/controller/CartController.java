package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.service.ICartService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/cart")
@RestController
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    /**
     * addToCart(Integer uid, Integer pid,
     *           Integer num, String username)
     *    因为业务层中需要上面4个参数，uid和username可以根据session获取
     * @param pid
     * @param amount
     * @param session
     * @return
     */

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.addToCart(
                getuidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }
}
