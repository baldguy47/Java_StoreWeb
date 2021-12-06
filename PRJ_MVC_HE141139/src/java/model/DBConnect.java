/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author I'mTrung
 */
public class DBConnect {

    public Connection conn = null;

    public DBConnect(String URL, String userName, String pass) {
        try {
            // Call driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // get Connection
            conn = DriverManager.getConnection(URL, userName, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
    public  DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=PRJ301E4","sa","123456");
    }
    public ResultSet getData(String sql){
                ResultSet rs=null;
        try {
            Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return rs;
    }
    public static void main(String[] args) {
        new DBConnect();
    }
    
}
