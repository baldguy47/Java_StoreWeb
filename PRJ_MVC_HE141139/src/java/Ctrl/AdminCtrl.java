/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOAdmin;
import model.DBConnect;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "AdminCtrl", urlPatterns = {"/AdminCtrl"})
public class AdminCtrl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") != null) {
                DAOAdmin dao = new DAOAdmin();
                String service = request.getParameter("service");

                if (service == null) {
                    service = "listAdmin";
                }
                if (service.equals("Product")) {
                    ResultSet rs = dao.getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                            + "from Product p\n"
                            + "left join Category c on p.cateID=c.cateID");
                    String title = "List all Product";
                    ResultSet rsCate = new DBConnect().getData("select * from Category");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("ketqua", rs);
                    request.setAttribute("title", title);
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/AdminProductView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equalsIgnoreCase("listAdmin")) {
                    String sql = "select * from Admin";
                    ResultSet rs = dao.getData(sql);
                    String title = "List all Admin";

                    request.setAttribute("ketqua", rs);
                    request.setAttribute("title", title);

                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/AdminView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equalsIgnoreCase("addAdmin")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {

                        RequestDispatcher disp = request.getRequestDispatcher("jsp/Admin_Add.html");
                        //run
                        disp.forward(request, response);
                    } else {
                        String username = request.getParameter("user");
                        String password = request.getParameter("password");

                        Admin admin = new Admin(username, password);
                        dao.addAdmin(admin);
                        response.sendRedirect("AdminCtrl");
                    }
                }

                if (service.equals("changePassword")) {
                    String findAccount = request.getParameter("changeSubmit");
                    if (findAccount == null) {
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/AdminFindAccount.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String username = request.getParameter("user");
                        ResultSet rs = dao.getData("select * from admin where username = '" + username + "'");
                        if (rs.next()) {

                            response.sendRedirect("AdminCtrl?service=changePasswordApproved&id=" + rs.getString(1));
                        } else {                        //check Username Existence

                            request.setAttribute("alert", "Username not found!!!");
                            RequestDispatcher disp = request.getRequestDispatcher("/jsp/AdminFindAccount.jsp");
                            //run
                            disp.forward(request, response);
                        }
                    }
                }
                if (service.equals("changePasswordApproved")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String id = request.getParameter("id");
                        ResultSet rs = dao.getData("select * from admin where adminID = " + id);
                        request.setAttribute("rsAdmin", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/AdminChangePassword.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String password = request.getParameter("password");
                        String username = request.getParameter("user");

                        dao.updatePassword(password, username);
                        response.sendRedirect("AdminCtrl");
                    }
                }
                if (service.equalsIgnoreCase("delete")) {
                    String id = request.getParameter("id");
                    dao.removeAdmin(Integer.parseInt(id));
                    response.sendRedirect("AdminCtrl");
                }
                if (service.equals("viewCate")) {
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    ResultSet rsCate = new DBConnect().getData("select * from Category");
                    ResultSet rsProduct = new DBConnect().getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                            + "from Product p\n"
                            + "left join Category c on p.cateID=c.cateID where"
                            + "  c.cateid=" + cateID);
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("ketqua", rsProduct);
                    request.getRequestDispatcher("jsp/AdminProductView.jsp").forward(request, response);

                }
            } else {
                response.sendRedirect("LoginCtrl");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
