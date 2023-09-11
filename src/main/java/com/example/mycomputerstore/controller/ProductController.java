package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.Product;
import com.example.mycomputerstore.service.IProductService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")

@RestController
public class ProductController extends BaseController{

    @Autowired
    private IProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK, data);
    }


    @GetMapping("/{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        JsonResult<Product> productJsonResult = new JsonResult<>(OK, data);
        System.out.println(productJsonResult);

        return productJsonResult;
    }
}
