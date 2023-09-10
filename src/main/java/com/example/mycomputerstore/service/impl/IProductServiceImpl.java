package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.entity.Product;
import com.example.mycomputerstore.mapper.ProductMapper;
import com.example.mycomputerstore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理商品数据的业务层实现类
 */

@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询热销商品的前四
     *
     * @return
     */
    @Override
    public List<Product> findHotList() {
        //获取到前四个热销商品
        List<Product> list = productMapper.findHotList();
        //将不需要展示的数据设置为null
        for(Product product:list){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return null;
    }
}
