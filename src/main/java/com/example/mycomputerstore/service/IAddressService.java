package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Address;

import java.util.List;

//收货地址业务层接口
public interface IAddressService {
    /**
     *这三个参数的由来:
     * 1.首先肯定要有address
     * 2.业务层需要根据uid查询该用户收货地址总数及新建地址时给字段uid赋值
     * 但新建收货地址的表单中并没有哪个控件让输入用户uid,所以需要控制层将uid传给业务层
     * 3.业务层在创建/修改收货地址时需要同时修改数据库中创建人/修改人的字段
     * 但新建收货地址的表单中并没有哪个控件让输入用户username,所以需要控制层将username传给业务层
     * 注意:> 可以用HttpSession session代替Integer uid, String username,但
     * 这样写的话就需要把BaseController类下获取uid,username的方法重新封装到一个
     * 类中并让IAddressServiceImp实现类继承该类,这样就需要微调一下代码逻辑,太麻
     * 烦,并且,最好每一层只处理该层需要做的事情,session对象是控制层传递的,所以就
     * 把session对象定义封装在控制层中,不需要在业务层中额外处理以降低耦合
     */
    void addAddress(Integer uid, String username, Address address);


    /**
     * 获取uid对应用户的所有地址信息,用于展示
     * @param uid 用户uid
48     * @return 地址信息list
     */
    List<Address> getByUid(Integer uid);
}
