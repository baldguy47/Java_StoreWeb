<%-- 
    Document   : BillView
    Created on : Oct 13, 2021, 9:48:27 PM
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
            <a href="CategoryCtrl">Category Management</a>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("billView");
        %>
        
        <table border="1">
            <thead>
                <tr>
                    <th>OID</th>
                    <th>Date Created</th>
                    <th>Customer Name</th>
                    <th>Customer Phone</th>
                    <th>Customer Address</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>Customer ID</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%= rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Process" : rs.getInt(7)==2 ? "Done":"Wait")%></td>
                    <td><%=rs.getString(8)%></td>
                    <td><a href="BillDetailCtrl?service=BillInfos&oID=<%=rs.getString(1)%>">Detail</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>
        <a href="MENU.html">BACK TO MENU</a>
    </body>
</html>
