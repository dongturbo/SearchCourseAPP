package com.dong.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
	 
	
	/* 
     * 打开数据库连接 
     */  
    public Connection openConnection() {  
        Properties prop = new Properties();  
        String driver = null;  
        String url = null;  
        //String username = null;  
        //String password = null;    
        try {  
            prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));    
            //driver = prop.getProperty("driver");  
            //url = prop.getProperty("url");  
            //username = prop.getProperty("username");  
            //password = prop.getProperty("password"); 
            url="jdbc:mysql://localhost:3306/schedule?useUnicode=true&characterEncoding=utf-8";
            driver="com.mysql.jdbc.Driver";
            Class.forName(driver);  
            return DriverManager.getConnection(url, "root","dong123");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }    
        return null;  
    }  
    
    
    public void closeConn(Connection conn){  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
