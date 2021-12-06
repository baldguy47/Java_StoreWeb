<%-- 
    Document   : addtoCart
    Created on : Oct 15, 2021, 7:58:05 PM
    Author     : I'mTrung
--%>

<%@page import="entity.Item"%>
<%@page import="model.DAOProduct"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String id = request.getParameter("pid");
            
            // ResultSet rs = dao.getData("select * from Product where pid = '" + id + "'");
            


        %>
        <h1>Item with id=<%=id%> was added to the Shopping Cart</h1>
        <h2><a href="jsp/Cart/showCart.jsp">Show Shopping Cart</a></h2>
    </body>
</html>
