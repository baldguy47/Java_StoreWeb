<%-- 
    Document   : CategoryView
    Created on : Oct 13, 2021, 9:11:17 PM
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

        <div style="width: 100%; height: 100px">
            <jsp:include page="../Head.jsp"></jsp:include>
            </div>

            <a href="AdminCtrl?service=addAdmin">Add Admin</a> |
            <a href="AdminCtrl?service=Product">Product Management</a> |
            <a href="CustomerCtrl">Customer Management</a> |
            <a href="BillCtrl">Bill Management</a> |
            <a href="CategoryCtrl">Category Management</a> |
        <%
            ResultSet rs = (ResultSet) request.getAttribute("cateView");

        %>
        <a href="CategoryCtrl?service=addCategory">Add Category</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=(rs.getInt(3) == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="CategoryCtrl?service=update&cateID=<%=rs.getInt(1)%>">Update</a></td>
                    <td><a href="CategoryCtrl?service=delete&cateID=<%=rs.getInt(1)%>">Delete</a></td>

                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>
        <a href="MENU.html">BACK TO MENU</a>
    </body>
</html>
