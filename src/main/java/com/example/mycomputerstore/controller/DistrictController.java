package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.entity.District;
import com.example.mycomputerstore.service.IDistrictService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/district")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    //district开头的请求都被拦截到getByParent()方法
    @GetMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }

}
