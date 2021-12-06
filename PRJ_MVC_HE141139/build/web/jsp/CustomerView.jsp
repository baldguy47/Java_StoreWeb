<%-- 
    Document   : CustomerView
    Created on : Oct 7, 2021, 12:45:18 PM
    Author     : I'mTrung
--%>

<%@page import="model.DAOCustomer"%>
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
            <div  style="margin-left: 30%; margin-right: auto ">
                <a href="AdminCtrl?service=addAdmin">Add Admin</a> |
                <a href="AdminCtrl?service=Product">Product Management</a> |
                <a href="CustomerCtrl">Customer Management</a> |
                <a href="BillCtrl">Bill Management</a> |
                <a href="CategoryCtrl">Category Management</a>
            </div>


            <table border="1" style="margin-top: 1%">
                <thead>
                    <tr>
                        <th>CID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Status</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    ResultSet rs = (ResultSet) request.getAttribute("ketqua");
                    while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="CustomerCtrl?service=delete&cid=<%=rs.getString(1)%>">Delete</a></td>
                    <td><a href="CustomerCtrl?service=update&cid=<%=rs.getString(1)%>">Update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>

    </body>
</html>
