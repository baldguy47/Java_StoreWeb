/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Item;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "Remove", urlPatterns = {"/RemoveCtrl"})
public class RemoveCtrl extends HttpServlet {

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
            String service = request.getParameter("service");
            if (service == null) {
                service = "Remove";
            }
            if (service.equalsIgnoreCase("Remove")) {
                HttpSession session = request.getSession();
                String pid = request.getParameter("pid");
                session.removeAttribute(pid);
                RequestDispatcher disp = request.getRequestDispatcher("jsp/Cart/showCart.jsp");
                //run
                disp.forward(request, response);
            }
            if (service.equalsIgnoreCase("RemoveAll")) {
                HttpSession session = request.getSession();
                java.util.Enumeration em = session.getAttributeNames();
                double total = Double.parseDouble(request.getParameter("total"));
                if (total != 0) {
                    while (em.hasMoreElements()) {
                        String id = em.nextElement().toString();
                        if (!id.equals("login") && !id.equals("admin")) {
                            session.removeAttribute(id);
                        }
                    }

                    response.sendRedirect("/PRJ_MVC_HE141139/MainCtrl");
                } else {
                    request.setAttribute("alert", "Your cart is empty");
                    RequestDispatcher disp = request.getRequestDispatcher("jsp/Cart/showCart.jsp");
                    //run
                    disp.forward(request, response);
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
