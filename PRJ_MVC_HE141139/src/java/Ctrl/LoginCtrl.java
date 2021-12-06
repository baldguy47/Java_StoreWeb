/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Account;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
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
import model.DAOCustomer;
import model.DAOLogin;
import model.DBConnect;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "LoginCtrl", urlPatterns = {"/LoginCtrl"})
public class LoginCtrl extends HttpServlet {

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
            DAOLogin dao = new DAOLogin();
            String service = request.getParameter("service");
            request.setAttribute("mess", "");
            if (service == null) {

                RequestDispatcher disp = request.getRequestDispatcher("/jsp/Login.jsp");
                //run
                disp.forward(request, response);
            }
            if (service.equals("login")) {
                //session.setMaxInactiveInterval(5);//set max inactive time for user
                String user = request.getParameter("username");
                String password = request.getParameter("password");
                Account adminAcc = dao.checkAdminLogin(user, password);
                //admin check
                if (adminAcc == null) {
                    Account customerAcc = dao.checkLogin(user, password);
                    //customer check
                    if (customerAcc == null) { // username not found !!!
                        request.setAttribute("mess", "Wrong user or password");
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/Login.jsp");
                        //run
                        disp.forward(request, response);
                    } else { // Login successful
                        session.setAttribute("login", user);
                        java.util.Enumeration em = session.getAttributeNames();
                        //checking guest added products to cart
                        while (em.hasMoreElements()) {

                            String id = em.nextElement().toString();
                            if (!id.equals("login") && !id.equals("admin")) {
                                RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cart/showCart.jsp");
                                //run
                                disp.forward(request, response);
                            } else {

                                response.sendRedirect("MainCtrl");
                            }
                        }
                    }
                } else {
                    session.setAttribute("admin", user);
                    response.sendRedirect("AdminCtrl");
                }
            }
            if (service.equals("logout")) {
                session.invalidate();

                response.sendRedirect("MainCtrl");
            }
            if (service.equals("register")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("./Register.html");
                } else {
                    DAOCustomer register = new DAOCustomer();
                    String name = request.getParameter("cname");
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    Customer cust = new Customer(name, phone, address, username, password);
                    int n = register.addCustomer(cust);
                    out.println(n);
                    if (n != 0) {
                        response.sendRedirect("./LoginCtrl");
                    } else {
                        response.sendRedirect("./Register.html");

                    }
                }

            }
            if (service.equals("changePassword")) {
                String submit = request.getParameter("changeSubmit");
                if (submit == null) {
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/FindAccount.jsp");
                    //run
                    disp.forward(request, response);
                } else {
                    String username = request.getParameter("user");
                    ResultSet rs = dao.getData("select * from customer where username = '" + username + "'");
                    if (rs.next()) {

                        response.sendRedirect("LoginCtrl?service=userApproved&id=" + rs.getString(1));
                    } else {                        //check Username Existence
                        ResultSet adminrs = dao.getData("select * from admin where username = '" + username + "'");
                        if (adminrs.next()) {
                            response.sendRedirect("LoginCtrl?service=adminApproved&id=" + adminrs.getString(1));
                        } else {
                            request.setAttribute("alert", "Username not found!!!");
                            RequestDispatcher disp = request.getRequestDispatcher("/jsp/FindAccount.jsp");
                            //run
                            disp.forward(request, response);
                        }

                    }
                }
            }
            if (service.equals("userApproved")) {
                DAOCustomer cusdao = new DAOCustomer();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cid = request.getParameter("id");
                    ResultSet rs = dao.getData("select * from customer where cid = " + cid);
                    request.setAttribute("rsCustomer", rs);
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerChangePassword.jsp");
                    //run
                    disp.forward(request, response);
                } else {
                    String id = request.getParameter("id");
                    String password = request.getParameter("password");

                    cusdao.updatePassword(Integer.parseInt(id), password);
                    response.sendRedirect("LoginCtrl");
                }
            }
            if (service.equals("adminApproved")) {
                    String submit = request.getParameter("submit");
                    DAOAdmin admindao = new DAOAdmin();
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

                        admindao.updatePassword(password, username);
                        response.sendRedirect("LoginCtrl");
                    }
                }

        } catch (SQLException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
