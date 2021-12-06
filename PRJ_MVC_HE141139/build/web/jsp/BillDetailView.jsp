<%-- 
    Document   : BillDetailView
    Created on : Oct 13, 2021, 9:58:51 PM
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
            <a href="AdminCtrl?service=addAdmin">Add Admin</a><br>
            <a href="AdminCtrl?service=changePassword">Change Password</a><br>
            <a href="AdminCtrl?service=Product">Product Management</a><br>
            <a href="CustomerCtrl">Customer Management</a><br>
            <a href="BillCtrl">Bill Management</a><br>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("detailRS");
            if (rs.next()) {
        %>
        <h4>Customer: <%=rs.getString(6)%></h4>
        <form action="BillCtrl?service=changeStatus&oID=<%=rs.getString(2)%>" method="post">
            <b>  Status</b>
            <input type = "radio" name = "status" value ="0" <%=(rs.getInt(13) == 0 ? "checked" : "")%>/>Wait
            <input type = "radio" name = "status" value ="1" <%=(rs.getInt(13) == 1 ? "checked" : "")%>/>Process
            <input type = "radio" name = "status" value ="2" <%=(rs.getInt(13) == 2 ? "checked" : "")%>/>Done
            <br>
            <input type="submit" name="submit" value="Update"/>
        </form>
        <%}%>


        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Order ID</th>

                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>

                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>
    </body>
</html>
