package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.Product;
import com.example.mycomputerstore.service.IProductService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController{

    @Autowired
    private IProductService productService;

    @GetMapping("/hot_list")
    public JsonResult<List<Product>> findHotList(){
        List<Product> list = productService.findHotList();
        //记得将数据传入
        return new JsonResult<List<Product>>(OK,list);
    }
}
