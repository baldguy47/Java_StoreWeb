/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

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
import model.DBConnect;

/**
 *
 * @author I'mTrung
 */
@WebServlet(name = "MainCtrl", urlPatterns = {"/MainCtrl"})
public class MainCtrl extends HttpServlet {

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
            String go = request.getParameter("go");
            if (go == null) {
                go = "start";
            }

            if (go.equals("start")) {
                /* TODO output your page here. You may use following sample code. */
                ResultSet rsCate = new DBConnect().getData("select * from Category");
                ResultSet rs = new DBConnect().getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                        + "from Product p\n"
                        + "left join Category c on p.cateID=c.cateID where p.status=1");
                request.setAttribute("rsCate", rsCate);
                request.setAttribute("ketqua", rs);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            if (go.equals("viewCate")) {
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                ResultSet rsCate = new DBConnect().getData("select * from Category");
                ResultSet rsProduct = new DBConnect().getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                        + "from Product p\n"
                        + "left join Category c on p.cateID=c.cateID where p.status=1"
                        + " and c.cateid=" + cateID);
                request.setAttribute("rsCate", rsCate);
                request.setAttribute("ketqua", rsProduct);
                request.getRequestDispatcher("/index.jsp").forward(request, response);

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
