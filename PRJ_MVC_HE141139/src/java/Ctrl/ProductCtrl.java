/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ctrl;

import entity.Bill;
import entity.BillDetail;
import entity.Item;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ProductCtrl", urlPatterns = {"/ProductCtrl"})
public class ProductCtrl extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            DAOBill billDAO = new DAOBill();
            String service = request.getParameter("service");
            HttpSession session = request.getSession();
            if (session.getAttribute("login") != null && session.getAttribute("admin") != null) {
                session.removeAttribute("admin");
            }
            if (service == null) {
                service = "listProduct";
            }
            if (service.equalsIgnoreCase("listProduct")) {
                ResultSet rs = dao.getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                        + "from Product p\n"
                        + "left join Category c on p.cateID=c.cateID where p.status=1");
                String title = "List all Product";

                request.setAttribute("ketqua", rs);
                request.setAttribute("title", title);

                RequestDispatcher disp = request.getRequestDispatcher("/jsp/ProductView.jsp");
                //run
                disp.forward(request, response);
            }

            if (service.equalsIgnoreCase("insert")) {
                if (session.getAttribute("admin") != null) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {

                        ResultSet rsCate = dao.getData("select * from Category");

                        request.setAttribute("rsCate", rsCate);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/ProductInsert.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String id = request.getParameter("pid");
                        String name = request.getParameter("name");
                        String quantity = request.getParameter("quantity");
                        String price = request.getParameter("price");
                        String Image = request.getParameter("Image");
                        String description = request.getParameter("description");
                        String cid = request.getParameter("cate");

                        //check validate: null, number, empty
                        if (id == null || id.equals("")) {
                            out.print("input ID");
                        }
                        int quan = Integer.parseInt(quantity);
                        double pri = Double.parseDouble(price);
                        int cateID = Integer.parseInt(cid);
                        Product pro = new Product(id, name, quan, pri, Image, description, cateID);
                        dao.addProduct(pro);
                        response.sendRedirect("AdminCtrl?service=Product");
                    }
                } else {
                    response.sendRedirect("/PRJ_MVC_HE141139/LoginCtrl");
                }
            }
            if (service.equalsIgnoreCase("update")) {
                if (session.getAttribute("admin") != null) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String pid = request.getParameter("pid");
                        ResultSet rs = dao.getData("select * from Product where pid = '" + pid + "'");
                        ResultSet rsCate = dao.getData("select * from Category ");
                        request.setAttribute("rs", rs);
                        request.setAttribute("rsCate", rsCate);
                        RequestDispatcher disp = request.getRequestDispatcher("/jsp/updateProduct.jsp");
                        //run
                        disp.forward(request, response);
                    } else {
                        String id = request.getParameter("pid");
                        String name = request.getParameter("name");
                        String quantity = request.getParameter("quantity");
                        String price = request.getParameter("price");
                        String Image = request.getParameter("Image");
                        String description = request.getParameter("description");
                        String status = request.getParameter("status");
                        String cid = request.getParameter("cate");

                        //check validate: null, number, empty
                        if (id == null || id.equals("")) {
                            out.print("input ID");
                        }
                        int quan = Integer.parseInt(quantity);
                        double pri = Double.parseDouble(price);
                        int sta = Integer.parseInt(status);
                        int cateID = Integer.parseInt(cid);
                        Product pro = new Product(id, name, quan, pri, Image, description, sta, cateID);
                        dao.updateProduct(pro);
                        response.sendRedirect("AdminCtrl?service=Product");
                    }
                } else {
                    response.sendRedirect("/PRJ_MVC_HE141139/LoginCtrl");
                }
            }

            if (service.equalsIgnoreCase("delete")) {
                if (session.getAttribute("admin") != null) {
                    String id = request.getParameter("pid");

                    dao.removeProduct(id);
                    response.sendRedirect("AdminCtrl?service=Product");
                } else {
                    response.sendRedirect("/PRJ_MVC_HE141139/LoginCtrl");
                }
            }

            if (service.equals("addtocart")) {
                if (session.getAttribute("admin") != null) {
                    session.removeAttribute("admin");
                }
                ResultSet rs = dao.getData("select p.pid, p.pname, p.quantity, p.price, p.image, p.description, p.status,c.cateName \n"
                        + "from Product p\n"
                        + "left join Category c on p.cateID=c.cateID");
                String title = "List all Product";

                request.setAttribute("ketqua", rs);
                request.setAttribute("title", title);
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cart/itemList.jsp");
                //run
                disp.forward(request, response);
            }
            if (service.equalsIgnoreCase("addingItemtoCart")) {

                String pid = request.getParameter("pid");
                ResultSet rs = dao.getData("select * from Product where pid = '" + pid + "'");

                if (rs.next()) {
                    String name = rs.getString(2);
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    String Image = rs.getString(5);
                    String description = rs.getString(6);
                    int status = 1;
                    int cid = rs.getInt(8);

                    Product pro = new Product(pid, name, quantity, price, Image, description, status, cid);
                    //Item item = new Item();
                    Item item = (Item) session.getAttribute(pid);
                    if (item == null) {
                        item = new Item(pro, 1);

                        session.setAttribute(pid, item);
                        // check
                        // System.out.println(session.getAttribute(id));

                    } else {
                        if (item.getQuantity() < pro.getQuantity()) {
                            Item dupItem = new Item(item.getProduct(), item.getQuantity());
                            dupItem.setProduct(pro);
                            dupItem.setQuantity(item.getQuantity() + 1);
                            session.setAttribute(pid, dupItem);
                        } else {
                            request.setAttribute("quantitymess", "exceed product quantity");
                            RequestDispatcher disp = request.getRequestDispatcher("/MainCtrl");
////                //run
                          disp.forward(request, response);
                            
                        }
                        //check
                        //System.out.println(session.getAttribute(id));
                    }
                }
                //System.out.println(session.getAttribute("pid").toString()); this line makes addtoCart.jsp fails to run
                //response.sendRedirect("MainCtrl");
                RequestDispatcher disp = request.getRequestDispatcher("/MainCtrl");
////                //run
                          disp.forward(request, response);

            }
            if (service.equalsIgnoreCase("showcart")) {
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cart/showCart.jsp");
//                //run
                disp.forward(request, response);
            }
            if (service.equals("checkout")) {

                if (session.getAttribute("admin") != null) {
                    session.removeAttribute("admin");
                }
                if (session.getAttribute("login") != null) {
                    java.util.Enumeration em = session.getAttributeNames();
                    DAOBillDetail bdDAO = new DAOBillDetail();
                    Date now = new Date();

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");

                    String strDate = sdfDate.format(now);
                    double total = 0;

                    String OID = "O" + strDate;
                    String sql = "select * from Customer where username = '" + session.getAttribute("login") + "'";
                    ResultSet rs = dao.getData(sql);

                    if (rs.next()) {
                        String name = rs.getString(2);
                        String phone = rs.getString(3);
                        String address = rs.getString(4);
                        String cusID = rs.getString(1);
                        while (em.hasMoreElements()) {

                            String id = em.nextElement().toString();
                            if (!id.equals("login") && !id.equals("admin")) {
                                Item item = (Item) session.getAttribute(id);
                                Product pro = item.getProduct();
                                total = Double.parseDouble(request.getParameter("total"));

                                if (total == item.getQuantity() * pro.getPrice()) {
                                    Bill bill = new Bill(OID, "", name, phone, address, total, 1, Integer.parseInt(cusID));
                                    billDAO.addBill(bill);
                                    BillDetail bd = new BillDetail(pro.getPid(), OID, item.getQuantity(), pro.getPrice(), pro.getPrice() * item.getQuantity());
                                    bdDAO.addBillDetail(bd);
                                } else {
                                    request.setAttribute("alert", "Quantity have not been Updated.");
                                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cart/showCart.jsp");
//                //run
                                    disp.forward(request, response);
                                }

                            }
                        }

                    }
                    //session.invalidate();

                    response.sendRedirect("RemoveCtrl?service=RemoveAll&total=" + total);

                } else {
                    request.setAttribute("mess", "Please Login First!");
                    RequestDispatcher disp = request.getRequestDispatcher("/jsp/Login.jsp");
//                //run
                    disp.forward(request, response);
                }
            }
            if (service.equals("updated")) {
                java.util.Enumeration em = session.getAttributeNames();
                int increase = 0;

                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();

                    if (!id.equals("login") && !id.equals("admin")) {
                        Item item = (Item) session.getAttribute(id);
                        increase++;

                        item.setQuantity(Integer.parseInt(request.getParameter("quantity" + increase)));
                    }
                }
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cart/showCart.jsp");
                //run
                disp.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
