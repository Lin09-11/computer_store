package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.VO.CartVO;
import com.example.mycomputerstore.entity.Cart;
import com.example.mycomputerstore.entity.Product;
import com.example.mycomputerstore.mapper.CartMapper;
import com.example.mycomputerstore.mapper.ProductMapper;
import com.example.mycomputerstore.service.ICartService;
import com.example.mycomputerstore.service.ex.AccessDeniedException;
import com.example.mycomputerstore.service.ex.CartNotFoundException;
import com.example.mycomputerstore.service.ex.InsertException;
import com.example.mycomputerstore.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
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

    /**
     *  更新用户的购物车数量的数据
     *
     * 调用了updateNumByCid(cid,num,modifiedUser,modifiedTime)
     *      和findByCid(cid)
     *  Integer:返回的是最新的购物车数量
     */
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("购物车数据不存在");
        } else if(! result.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }

        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows != 1) {
            throw new UpdateException("更新数据失败");
        }

        return num;
    }

    /**
     * 更新用户的购物车数据--减少
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    @Override
    public Integer subNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num=result.getNum()-1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows!=1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }


    /**
     * 将用户在购物车页面中勾选中的数据传递给"结算“页面
     *
     * @param uid
     * @param cids
     * @return
     */
    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        //循环遍历获取到的数据，查看该数据是否与目前传入的uid是一致的
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()){//如果循环没有遍历结束
            //因为it一开始是指向下标为0，所以一上来就要next，才可以指向下一个
            CartVO cartVO = it.next();
            //进行判断用户对应是否正确
            if (!cartVO.getUid().equals(uid)) {//表示当前数据不属于当前的用户
                //从集合中移除这个元素
                list.remove(cartVO);
            }
        }

        return list;
    }
}
