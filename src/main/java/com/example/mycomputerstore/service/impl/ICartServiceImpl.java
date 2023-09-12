package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.VO.CartVO;
import com.example.mycomputerstore.entity.Cart;
import com.example.mycomputerstore.entity.Product;
import com.example.mycomputerstore.mapper.CartMapper;
import com.example.mycomputerstore.mapper.ProductMapper;
import com.example.mycomputerstore.service.ICartService;
import com.example.mycomputerstore.service.ex.InsertException;
import com.example.mycomputerstore.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ICartServiceImpl implements ICartService {
    /*
    由于购物车的业务依赖于购物车和商品的持久层，若通过session获取数据
     */
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询购物车是否已经存在【查看用户之前是否将该商品加入过购物车】
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null) {//新增商品
            Cart cart = new Cart();
            //封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //查询商品数据，得到商品价格进行封装
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            //封装数据:4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            Integer rows = cartMapper.insert(cart);

            if(rows != 1) {
                throw new InsertException("未知异常 在 插入数据");
            }
        } else {//更新num值
            //从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer num = result.getNum() + amount;//加入购物车时只会有+不可能有-

            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);

            if(rows != 1) {
                throw new UpdateException("未知异常 在 更新数据");
            }
        }
    }

    /**
     * 根据用户id查询购物车
     * @param uid
     * @return
     */
    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }
}
