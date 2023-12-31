package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.VO.CartVO;
import com.example.mycomputerstore.service.ICartService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    /**
     * addToCart(Integer uid, Integer pid,
     * Integer num, String username)
     * 因为业务层中需要上面4个参数，uid和username可以根据session获取
     *
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

    /**
     * 根据用户id查询购物车
     * @param session: 为了获取用户的uid
     * @return
     */
    @GetMapping({"/",""})//表示直接输入/cart就可以访问
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 在购物车列表中增加商品数量
     * @param cid
     * @param session
     * @return
     */
    @PostMapping("/{cid}/add_num")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid,
                                      HttpSession session){
        Integer data = cartService.addNum(cid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 在购物车列表中减少商品数量
     * @param cid
     * @param session
     * @return
     */
    @PostMapping("/sub_num/{cid}")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid,
                                      HttpSession session){
        Integer data = cartService.subNum(cid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 将用户在购物车选中的商品通过”结算“传递到结算页面
     * @param cids
     * @param session
     * @return
     */
    @GetMapping("/list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,
                                               HttpSession session){
        List<CartVO> data = cartService.getVOByCid(getuidFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }
}
