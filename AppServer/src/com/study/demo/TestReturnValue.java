package com.study.demo;


public class TestReturnValue {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        
        String sql = "ABC ?1";
        String newSql = sql.replace("?1", "dfghijk");
        System.out.println(newSql);
        
    }
}
