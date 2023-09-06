package com.example.mycomputerstore.service.impl;

import com.example.mycomputerstore.entity.District;
import com.example.mycomputerstore.mapper.DistrictMapper;
import com.example.mycomputerstore.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IDistrictServiceImpl implements IDistrictService {


    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 根据父代号查找区域
     *
     * @param parent
     * @return
     */
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        //在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null
        //可以节省浏览，另一方面提升效率
        for (District d:list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
