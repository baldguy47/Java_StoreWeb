/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author I'mTrung
 */
public class DAOAdmin extends DBConnect {

    public int addAdmin(Admin admin) {
        int n = 0;
        String sql = "insert into Admin(username,password)\n"
                + "values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getUsername());
            pre.setString(2, admin.getPassword());
            
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void updatePassword(String changedPass, String user) {
        String sql = "update Admin set password = ? where username = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, changedPass);
            pre.setString(2, user);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int removeAdmin(int id) {
        String sql = "delete from Admin where adminID = ?";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;

    }

    public boolean login(String userName, String password) {
        boolean flag = false;
        String sql = "select * from admin where username=? and password = ? and status = 1";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.set....
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }
    public void displayAll() {
        String sql = "select * from Admin";
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                String id = rs.getString(1), username = rs.getString(2);
                String password = rs.getString(3);
                
                Admin admin = new Admin(id, username, password);
                System.out.println(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        //int n = dao.addAdmin(new Admin("admin2", "password1"));
        int n = dao.removeAdmin(1);
        //dao.updatePassword("changedpassword1", 1);
        if (n > 0) {
            System.out.println("Admin added successfuly");
        } 
    }
}
