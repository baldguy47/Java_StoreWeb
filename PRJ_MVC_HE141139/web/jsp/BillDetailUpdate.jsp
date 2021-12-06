<%-- 
    Document   : BillDetailUpdate
    Created on : Oct 15, 2021, 6:48:07 PM
    Author     : I'mTrung
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String mess = (String) session.getAttribute("admin");
            if (mess != null) {
                out.println("<h3> Welcome "+mess+"</h3>");
            }
        %>
        <a href="AdminCtrl?service=addAdmin">Add Admin</a><br>
        <a href="AdminCtrl?service=changePassword">Change Password</a><br>
        <a href="AdminCtrl?service=Product">Product Management</a><br>
        <a href="CustomerCtrl">Customer Management</a><br>
        <a href="BillCtrl">Bill Management</a><br>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("detailRS");
            if(rs.next()){
        %>
        <form action="BillDetailCtrl?service=updateQuantity" method="POST">
            <table border="1">

                <tr>
                    <th>Product ID</th>
                    <th><input type="text" name="pid" value="<%=rs.getString(1)%>"/></th>
                </tr>
                <tr>
                    <th>Bill ID</th>
                    <th><input type="text" name="oID" value="<%=rs.getString(2)%>"/></th>
                </tr>
                
                <tr>
                    <th>Quantity </th>
                    <th><input type="text" name="quantity" value="<%=rs.getInt(3)%>"/></th>
                </tr>
                <tr>
                    <th>Status</th>
                    <th><input type = "radio" name = "status" value ="0" <%=(rs.getInt(7) == 0 ? "checked" : "")%>/>Wait</th>
                    <th><input type = "radio" name = "status" value ="1" <%=(rs.getInt(7) == 1 ? "checked" : "")%>/>Process</th>
                    <th><input type = "radio" name = "status" value ="2" <%=(rs.getInt(7) == 2 ? "checked" : "")%>/>Done</th>
                </tr>
                <tr>
                    <td><input type = "submit" name = "submit" value ="Update"/></td>
                    <td><input type = "Reset"  value ="Reset"/></td>
                </tr>
            </table>
                <%}%>
        </form>
    </body>
</html>
