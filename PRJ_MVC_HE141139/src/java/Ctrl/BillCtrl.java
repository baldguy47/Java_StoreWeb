/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Bill;
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

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "BillCtrl", urlPatterns = {"/BillCtrl"})
public class BillCtrl extends HttpServlet {

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
            DAOBill dao = new DAOBill();
            String service = request.getParameter("service");
            if (session.getAttribute("login") != null) {
                //delivering
                // 0 - wait
                // 1 - process
                // 2 - done
                if (service == null) {
                    service = "delivering";
                }
                if (service.equals("delivering")) {
                    String user = (String) session.getAttribute("login");
                    ResultSet rs = dao.getData("select b.* from bill b,customer c where c.cid=b.cid and c.username='" + user + "'\n");
                    request.setAttribute("billView", rs);
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerBillView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equals("filter")) {
                    String user = (String) session.getAttribute("login");
                    String status = request.getParameter("status");
                    if (status.equals("wait")) {
                        ResultSet rs = dao.getData("select b.* from bill b,customer c where c.cid=b.cid and c.username='" + user + "'"
                                + "\n and b.status  = 0");
                        request.setAttribute("billView", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerBillView.jsp");
                        //run
                        disp.forward(request, response);
                    }
                    if (status.equals("process")) {
                        ResultSet rs = dao.getData("select b.* from bill b,customer c where c.cid=b.cid and c.username='" + user + "'"
                                + "\n and b.status = 1");
                        request.setAttribute("billView", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerBillView.jsp");
                        //run
                        disp.forward(request, response);
                    }
                    if (status.equals("done")) {
                        ResultSet rs = dao.getData("select b.* from bill b,customer c where c.cid=b.cid and c.username='" + user + "'"
                                + "\n and b.status  = 2");
                        request.setAttribute("billView", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/CustomerBillView.jsp");
                        //run
                        disp.forward(request, response);
                    }
                }
                if (service.equals("update")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String id = request.getParameter("oID");
                        ResultSet rs = dao.getData("select * from bill b\n"
                                + "where b.oID= '" + id + "'");

                        request.setAttribute("billUpdate", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/BillUpdate.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String oID = request.getParameter("oID");

                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");

                        Bill bill = new Bill(oID, name, phone, address);

                        dao.updateBill(bill);
                        response.sendRedirect("./BillCtrl");
                    }
                }

            }
            if (session.getAttribute("admin") != null && session.getAttribute("login") == null) {

                if (service == null) {
                    service = "listBill";
                }
                if (service.equalsIgnoreCase("listBill")) {
                    ResultSet rs = dao.getData("select * from bill b\n");
                    request.setAttribute("billView", rs);
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/BillView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equals("changeStatus")) {
                    String submit = request.getParameter("submit");
                    if (submit != null) {
                        String oid = request.getParameter("oID");
                        int status = Integer.parseInt(request.getParameter("status"));
                        dao.changeStatus(oid, status);
                        response.sendRedirect("./BillCtrl");
                    }
                }

            }
            if (session.getAttribute("admin") == null && session.getAttribute("login") == null) {
                response.sendRedirect("./LoginCtrl");
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
