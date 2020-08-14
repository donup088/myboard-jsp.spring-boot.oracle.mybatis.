package com.dong.myboard.Jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.fail;

public class JDBCTest {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                "book_ex3", "book_ex3")){
            System.out.println("con: " + con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}