/*
 * To change this license header, choose License Headers in billject billperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author I'mTrung
 */
public class DAOBill extends DBConnect {

    

    public int addBill(Bill bill) {
        int n = 0;
        String sql = "INSERT INTO [Bill]([oID],[dateCreate],[cname],[cphone],[cAddress],[total],[status],[cid])\n"
                + " VALUES(?,?,?,?,?,?,?,?)";
        LocalDateTime ldt = LocalDateTime.now();
        String dateCreate = ldt.toString();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getoID());
            pre.setString(2, dateCreate);
            pre.setString(3, bill.getCname());
            pre.setString(4, bill.getCphone());
            pre.setString(5, bill.getcAddress());
            pre.setDouble(6, bill.getTotal());
            pre.setInt(7, bill.getStatus());
            pre.setInt(8, bill.getCid());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void updateBill(Bill bill) {
        String sql = "Update bill set cname = ?, cphone=?, cAddress=? \n "
                + "where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getCname());
            pre.setString(2, bill.getCphone());
            pre.setString(3, bill.getcAddress());
            pre.setString(4, bill.getoID());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateTotal(Bill bill) {
        String sql = "Update bill set total = ? \n "
                + "where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, bill.getTotal());
            pre.setString(2, bill.getoID());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int removeBill(String id) {
        int n = 0;
        String sql = "Delete from Bill where oID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void changeStatus(String id, int status) {
        String sql = "update Bill set status = ? where oID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBill(String id) {
        int n = 0;
        String sql = "select * from Bill where oID = ?";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                n++;
            }
            if (n <= 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void displayAll() {
        String sql = "select * from Bill";
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                String id = rs.getString(1);
                String datecreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);

                Bill bill = new Bill(id, datecreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
        DAOProduct pro = new DAOProduct();

//        String pid = "P01";
//        int n = dao.addBill(new Bill("O01", dateCreate, "billName", "123456789", "Earth", pro.getPrice(pid) * pro.getQuantity(pid), 1, 1));
//
//        if (n > 0) {
//            System.out.println("Customer added successfuly");
//        }
    }
}
