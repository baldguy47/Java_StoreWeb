/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Ctrl.LoginCtrl;
import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author I'mTrung
 */
public class DAOLogin extends DBConnect {

    public Account checkLogin(String username, String password) {
        String sql = "select * from Customer where username =?  and password = ? and status = 1";

        ResultSet rs;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString(5), rs.getString(6));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Account checkAdminLogin(String username,String password){
        String sql = "select * from Admin where username =?  and password = ? ";

        ResultSet rs;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString(2), rs.getString(3));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
