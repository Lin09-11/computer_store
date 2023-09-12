package com.example.mycomputerstore.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 不需要继承BaseController类,那相应的就需要单独实现Serializable接口
 * 购物车数据的VO类（Value Object）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}

