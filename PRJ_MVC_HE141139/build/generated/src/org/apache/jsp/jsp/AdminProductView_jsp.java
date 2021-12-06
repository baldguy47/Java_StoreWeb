package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;

public final class AdminProductView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
String mess = (String) session.getAttribute("admin");
            if (mess != null) {
                out.println("<h3> Welcome "+mess+"</h3>");
            }
      out.write("\n");
      out.write("        ");

            String adminLogin = (String) session.getAttribute("admin");
            if(adminLogin!=null){
                out.println("<a href=\"ProductCtrl?service=insert\">Add Product</a><br>");
            }
        
      out.write("\n");
      out.write("        <a href=\\\"LoginCtrl?service=logout\\\">Logout</a>\n");
      out.write("        \n");
      out.write("        ");

            ResultSet rs = (ResultSet) request.getAttribute("ketqua");
            String title = (String) request.getAttribute("title");
            
        
      out.write(" \n");
      out.write("         \n");
      out.write("        <table border=\"1\">\n");
      out.write("            <caption>");
      out.print(title);
      out.write("</caption>\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>PID</th>\n");
      out.write("                    <th>Product Name</th>\n");
      out.write("                    <th>Quantity</th>\n");
      out.write("                    <th>Price</th>\n");
      out.write("                    <th>Image</th>\n");
      out.write("                    <th>Description</th>\n");
      out.write("                    <th>Status</th>\n");
      out.write("                    <th>Category</th>\n");
      out.write("                    <th>Update</th>\n");
      out.write("                    <th>Delete</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("\n");
      out.write("            <tbody>\n");
      out.write("                ");

                    while (rs.next()) {
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(rs.getString(1));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getString(2));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getInt(3));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getDouble(4));
      out.write("</td>\n");
      out.write("                    <td><img src=\"/img/");
      out.print(rs.getString(5));
      out.write(".PNG\" width=\"100px\" height=\"150px\"></td>\n");
      out.write("                    <td>");
      out.print(rs.getString(6));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print((rs.getInt(7) == 1 ? "Enable" : "Disable"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getString(8));
      out.write("</td>\n");
      out.write("                    <td><a href=\"ProductCtrl?service=update&pid=");
      out.print(rs.getString(1));
      out.write("\">update</a></td>\n");
      out.write("                    <td><a href=\"ProductCtrl?service=delete&pid=");
      out.print(rs.getString(1));
      out.write("\">delete</a></td>\n");
      out.write("\n");
      out.write("                </tr>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("        <br>\n");
      out.write("        <br>\n");
      out.write("        <a href=\"MENU.html\">BACK TO MENU</a>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
