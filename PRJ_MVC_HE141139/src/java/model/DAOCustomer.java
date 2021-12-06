/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author I'mTrung
 */
public class DAOCustomer extends DBConnect {

    public int addCustomer(Customer cust) {
        int n = 0;
        String sql = "insert into Customer(cname,cphone,cAddress,username,password,status) \n"
                + "values(?,?,?,?,?,1)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cust.getCname());
            pre.setString(2, cust.getCphone());
            pre.setString(3, cust.getcAddress());
            pre.setString(4, cust.getUsername());
            pre.setString(5, cust.getPassword());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int updateCustomer(Customer cust) {
        int n = 0;
        String sql = "update Customer set cname=?,cphone=?,cAddress=?,status=? \n"
                + "where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cust.getCname());
            pre.setString(2, cust.getCphone());
            pre.setString(3, cust.getcAddress());
            pre.setInt(4, cust.getStatus());
            pre.setInt(5, cust.getCid());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int removeCustomer(int id) {
        int n = 0;
        String sql = "delete from Customer where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void changeStatus(String id, int status) {
        String sql = "update Customer set status = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(int id, String username, String password) {
        String sql = "update Customer set username = ?, password = ? where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            pre.setInt(3, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(int id, String password) {
        String sql = "update Customer set password = ? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayAll() {
        String sql = "select * from BillDetail";
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                int cid = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String adress = rs.getString(4);
                String user = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);

                Customer cus = new Customer(cid, name, phone, adress, user, password, status);
                System.out.println(cus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        int n = dao.addCustomer(new Customer("cust1", "123456789", "address", "username1", "password", 1));
        //int n = dao.removeCustomer(1);
        //dao.updateAccount(1, "abczxc", "zxcvda");
        dao.updatePassword(1, "zxcv");
        if (n > 0) {
            System.out.println("Customer added successfuly");
        } else {
            System.err.println("Customer add error!");
        }
    }
}
