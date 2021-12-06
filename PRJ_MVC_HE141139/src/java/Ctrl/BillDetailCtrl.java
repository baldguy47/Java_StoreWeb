/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Bill;
import entity.BillDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOProduct;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "BillDetailCtrl", urlPatterns = {"/BillDetailCtrl"})
public class BillDetailCtrl extends HttpServlet {

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
            if ( session.getAttribute("admin") != null) {
                DAOBillDetail dao = new DAOBillDetail();
                DAOBill billdao = new DAOBill();
                DAOProduct prodao = new DAOProduct();
                String service = request.getParameter("service");
                if (service.equals("BillInfos")) {
                    ResultSet rs = dao.getData("select bd.*, b.cname,b.* from BillDetail bd, bill b \n"
                            + " where bd.oID = '" + request.getParameter("oID") + "' \n"
                            + "and b.oid=bd.oid");
                    request.setAttribute("detailRS", rs);
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/BillDetailView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equals("updateQuantity")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        ResultSet rs = dao.getData("select * from BillDetail where oID = '" + request.getParameter("oID") + "'");
                        request.setAttribute("detailRS", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/BillDetailUpdate.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String oid = request.getParameter("oID");
                        String pid = request.getParameter("pid");
                        int quan = Integer.parseInt(request.getParameter("quantity"));
                        double price = prodao.getPrice(pid);
                        double total = quan * price;
                        BillDetail bd = new BillDetail("", oid, quan, price, total);
                        dao.updateQuantity(bd);

                        Bill bill = new Bill(oid, "", "", "", "", total, 0, 0);

                        billdao.updateTotal(bill);
                        response.sendRedirect("BillCtrl");
                    }
                }
            } else {
                response.sendRedirect("LoginCtrl");
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
