/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Customer;
import entity.Product;
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
import model.DAOCustomer;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "CustomerCtrl", urlPatterns = {"/CustomerCtrl"})
public class CustomerCtrl extends HttpServlet {

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

            DAOCustomer dao = new DAOCustomer();
            String service = request.getParameter("service");
            if (session.getAttribute("admin") != null) {
                if (service == null) {
                    service = "listCustomer";
                }
                if (service.equalsIgnoreCase("listCustomer")) {
                    String sql = "select * from Customer";
                    ResultSet rs = dao.getData(sql);
                    String title = "List all Customer";

                    request.setAttribute("ketqua", rs);
                    request.setAttribute("title", title);

                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equalsIgnoreCase("addCustomer")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {

                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerAdd.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");
                        String username = request.getParameter("user");
                        String password = request.getParameter("password");
                        int status = Integer.parseInt(request.getParameter("status"));

                        Customer cus = new Customer(name, phone, address, username, password, status);
                        dao.addCustomer(cus);
                        response.sendRedirect("CustomerCtrl");
                    }
                }
                if (service.equalsIgnoreCase("update")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String cid = request.getParameter("cid");
                        ResultSet rs = dao.getData("select * from Customer where cid =" + cid);

                        request.setAttribute("rs", rs);

                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerUpdate.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String id = request.getParameter("cid");
                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");
                        String username = request.getParameter("user");
                        String password = "";
                        int status = Integer.parseInt(request.getParameter("status"));

                        Customer cus = new Customer(Integer.parseInt(id), name, phone, address, username, password, status);
                        dao.updateCustomer(cus);
                        response.sendRedirect("CustomerCtrl");
                    }
                }
                if (service.equalsIgnoreCase("delete")) {
                    String id = request.getParameter("cid");
                    dao.removeCustomer(Integer.parseInt(id));
                    response.sendRedirect("CustomerCtrl");
                }
            }

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
