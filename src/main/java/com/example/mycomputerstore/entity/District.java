package com.example.mycomputerstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示省市区的数据实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District extends BaseEntity {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
