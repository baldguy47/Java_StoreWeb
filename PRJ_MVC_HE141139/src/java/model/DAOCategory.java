/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author I'mTrung
 */
public class DAOCategory extends DBConnect{

    public int addCategory(Category cate ) {
        int n = 0;
        String sql = "insert into Category(cateName,status)\n"
                + "values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCatName());
            pre.setInt(2, cate.getStatus());
            

            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    public int updateCategory(Category cat) {
        int n = 0;
        String sql = "Update  Category set cateName=?,status=?\n"
                + "where cateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cat.getCatName());
            pre.setInt(2, cat.getStatus());
            pre.setInt(3, cat.getCateID());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
     public int removeCategory(String id) {
        int n = 0;
        String sql = "delete from Category where cateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            n=pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
       public void changeStatus(String id, int status) {
        String sql = "update Category set status = ? where cateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, id);
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
                int status = rs.getInt(3);
                

                Category cat = new Category(cid, name, status);
                System.out.println(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        int n = dao.addCategory(new Category("Laptop", 1));
        if (n > 0) {
            System.out.println("Category added successfuly");
        } else {
            System.err.println("Category add error!");
        }
    }
}
