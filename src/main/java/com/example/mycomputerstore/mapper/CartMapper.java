package com.example.mycomputerstore.mapper;


import com.example.mycomputerstore.VO.CartVO;
import com.example.mycomputerstore.entity.Cart;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    /**
     * 根据用户的id和商品的id来查询购物车
     * @param uid
     * @return
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 新增购物车数据前，要先查看该用户的购物车数据是否存在
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);


    /**
     * 用于：显示勾选购物车数据
     * 根据购物车的id将用户选中的商品传递给“结算”页面
     * @param cids :因为传递过来的商品数量可能不只一条
     * @return
     */
    List<CartVO> findVOByCid(Integer[] cids);
}
