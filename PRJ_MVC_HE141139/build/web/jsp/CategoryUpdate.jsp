<%-- 
    Document   : CategoryUpdate
    Created on : Oct 13, 2021, 9:28:21 PM
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
        <a href="CategoryCtrl">Category Management</a><br>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("cateUpdate");
            if (rs.next()) {
        %>
        <form action="CategoryCtrl?service=update" method="POST">
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td><input type = "text" name = "id" value ="<%=rs.getInt(1)%>" readonly/></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type = "text" name = "name" value ="<%=rs.getString(2)%>"/></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type = "radio" name = "status" value ="1" <%=(rs.getInt(3) == 1 ? " checked" : "")%>/>Enable</td>
                    <td><input type = "radio" name = "status" value ="0" <%=(rs.getInt(3) == 0 ? " checked" : "")%>/>Disable</td>

                <tr>
                    <td><input type = "submit" name = "submit" value ="Update"/></td>
                    <td><input type = "Reset"  value ="Reset"/></td>
                </tr>
            </table>
        </form>
        <%}%>
    </body>
</html>
