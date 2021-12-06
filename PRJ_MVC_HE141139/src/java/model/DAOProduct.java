/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Ctrl.BillDetailCtrl;
import entity.BillDetail;
import entity.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author I'mTrung
 */
public class DAOProduct extends DBConnect {
//    DBConnect dbConn;
//    Connection connect;
//    
//    public DAOProduct(DBConnect dbconn){
//        this.dbConn = dbconn;
//        connect = dbconn.conn;
//    }
 
    public void sortProdAscending(){
        
    }
    public int addProduct(Product pro) {
        int n = 0;
        String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID)\n"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "Update  Product set pname=?,quantity=?,price=?,image=?,description=?,status=?,cateID=?\n"
                + "where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(8, pro.getPid());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void updateQuantity(String id, int quantity) {
        String sql = "update Product set quantity = quantity + ? where pid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(String id, int status) {
        String sql = "update Product set status = ? where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int removeProduct(String id) {
        int n = 0;
        //check foreignkey constraint
        String sql2 = "select * from BillDetail where pid ='" + id + "'";
        ResultSet rs = getData(sql2);
        try {
            if (rs.next()) {
                //change status
                DAOProduct pro = new DAOProduct();
                pro.changeStatus(id, 0);
                
            } else {
                //remove
                String sql = "delete from Product where pid = ?";
                try {
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, id);
                    n = pre.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public ArrayList<Product> getAll() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid"), pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Product> getPrice(double from, double to) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from Product where price between " + from + " and " + to + " ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString("pid"), pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void displayAll() {
        String sql = "select * from Product";
        ResultSet rs = getData(sql);
        try {

            while (rs.next()) {
                String pid = rs.getString("pid"), pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getPrice(String id) {
        double price = 0;
        String sql = "select p.price from Product p where pid = '"+id+"'";
        try {
           
            ResultSet rs = getData(sql);
            if (rs.next()) {
                price = rs.getDouble(1);
            }
            System.out.println(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public int getQuantity(String id) {
        int quantity = 0;
        String sql = "select p.quantity from Product p where pid = '"+id+"'";
        try {
        
            
            ResultSet rs = getData(sql);
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
            System.out.println(quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }
    
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        //int n = dao.insertProduct(new Product("P03", "HP G67", 2, 500, "no img", "2nd hand", 1, 1));
        //dao.updateQuantity("P03", 123);
        //int n = dao.updateProduct(new Product("P03", "HP G67", 2, 53300, "no img", "2nd hand", 1, 1) );
        //int n = dao.removeProduct("P03");
//        dao.updateQuantity("P02", 1);
//        dao.getAll();
        double price = dao.getPrice("P02");
        int quantity = dao.getQuantity("P02");
        System.out.println(price+" " + quantity);
//        if (n > 0) {
//            System.out.println("success");
//        }
        // dao.changeStatus("P03", 0);

    }
}
