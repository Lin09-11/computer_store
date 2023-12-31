package com.example.mycomputerstore.service;

import com.example.mycomputerstore.VO.CartVO;
import com.example.mycomputerstore.entity.Cart;

import java.util.List;

public interface ICartService {


    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid,
                   Integer amount, String username);

    /**
     * 根据用户id查询购物车
     * @param uid
     * @return
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 更新用户的购物车数量的数据--增加
     * <p>
     * 调用了updateNumByCid(cid,num,modifiedUser,modifiedTime)
     * 和findByCid(cid)
     * Integer:返回的是最新的购物车数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 更新用户的购物车数据--减少
     *
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer subNum(Integer cid, Integer uid, String username);


    /**
     * 将用户在购物车页面中勾选中的数据传递给"结算“页面
     * @param uid
     * @param cids
     * @return
     */
    List<CartVO> getVOByCid(Integer uid,
            Integer[] cids);
}
