/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Category;
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
import model.DAOCategory;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "CategoryCtrl", urlPatterns = {"/CategoryCtrl"})
public class CategoryCtrl extends HttpServlet {

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
            DAOCategory dao = new DAOCategory();
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") != null) {
                String service = request.getParameter("service");
                if (service == null) {
                    service = "listCategory";
                }
                if (service.equalsIgnoreCase("listCategory")) {
                    String sql = "select * from Category";
                    ResultSet rs = dao.getData(sql);
                    String title = "List all Category";

                    request.setAttribute("cateView", rs);
                    request.setAttribute("title", title);

                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/CategoryView.jsp");
                    //run
                    disp.forward(request, response);
                }
                if (service.equals("update")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String cateid = request.getParameter("cateID");
                        ResultSet rs = dao.getData("select * from category where cateID=" + cateid);
                        request.setAttribute("cateUpdate", rs);
                        RequestDispatcher disp = request.getRequestDispatcher("jsp/CategoryUpdate.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String id = request.getParameter("id");
                        String name = request.getParameter("name");
                        String status = request.getParameter("status");
                        Category cate = new Category(Integer.parseInt(id), name, Integer.parseInt(status));
                        dao.updateCategory(cate);
                        response.sendRedirect("CategoryCtrl");
                    }
                }
                if (service.equals("delete")) {
                    String id = request.getParameter("cateID");
                    dao.removeCategory(id);
                    response.sendRedirect("CategoryCtrl");
                }
                if (service.equals("addCategory")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        RequestDispatcher disp = request.getRequestDispatcher("/Category_Add.html");
                        //run
                        disp.forward(request, response);
                    } else {
                        String name = request.getParameter("name");
                        String status = request.getParameter("status");
                        Category cate = new Category(name, Integer.parseInt(status));
                        dao.addCategory(cate);
                        response.sendRedirect("CategoryCtrl");
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
