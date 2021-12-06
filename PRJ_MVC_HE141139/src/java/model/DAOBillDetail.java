/*
 * To change this license header, choose License Headers in bdject bdperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author I'mTrung
 */
public class DAOBillDetail extends DBConnect {
    public int addBillDetail(BillDetail bd){
        
        int n = 0;
        String sql = "insert into BillDetail(pid,oID,quantity,price,total)\n"
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bd.getPid());
            pre.setString(2, bd.getoID());
            pre.setInt(3, bd.getQuantity());
            pre.setDouble(4, bd.getPrice());
            pre.setDouble(5, bd.getQuantity()*bd.getPrice());

            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    public int removeBillDetail(String id){
        int n = 0;
        String sql = "Delete from BillDetail where oID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    public String getOrderID(String id) {
        String order = "";
        String sql = "select oID from BillDetail  where oID= '"+id+"'";
        try {
           
            ResultSet rs = getData(sql);
            if (rs.next()) {
                order = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
    public void displayAll() {
        String sql = "select * from BillDetail";
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                String pid = rs.getString(1);
                String oid = rs.getString(2);
                int quatity = rs.getInt(3);
                double price = rs.getDouble(4);
                double total = rs.getDouble(5);

                BillDetail bill = new BillDetail(pid, oid, quatity, price, total);
                System.out.println(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateQuantity(BillDetail bd){
        String sql = "update BillDetail set quantity=?,total=? where oID=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bd.getQuantity());
            pre.setDouble(2, bd.getTotal());
            pre.setString(3, bd.getoID());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        DAOProduct proDAO = new DAOProduct();
        DAOBill billDAO = new DAOBill();
        int n = 0;
        String id = "P01";
        String oid = "O01";
        if(billDAO.checkBill(oid)==true)
            n = dao.addBillDetail(new BillDetail(id, "O01", proDAO.getQuantity(id), proDAO.getPrice(id), 0));
        if(n>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed to add");
        }
        
    }
}
