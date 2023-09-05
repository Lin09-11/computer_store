package com.example.mycomputerstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class MyComputerStoreApplicationTests {


    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }

    /**
     * HikariProxyConnection@1967467242 wrapping com.mysql.cj.jdbc.ConnectionImpl@6c24f61d
     * 数据库连接：
     *  1.DBCP
     *  2.C3P0
     *  3.Hikari:管理数据库的连接对象
     * @throws Exception
     */
    @Test
    void getConnection() throws Exception{
        System.out.println(dataSource.getConnection());
    }


}
