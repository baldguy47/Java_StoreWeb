<%-- 
    Document   : CustomerBillView
    Created on : Nov 3, 2021, 5:59:41 AM
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
        <%
            ResultSet rs = (ResultSet) request.getAttribute("billView");
        %>
        <div style="width: 100%; height: 100px">
            <jsp:include page="../Head.jsp"></jsp:include>
            </div>
            <a href="BillCtrl?service=filter&status=wait">Wait</a> |
            <a href="BillCtrl?service=filter&status=process">Process</a> |
            <a href="BillCtrl?service=filter&status=done">Done</a><br>
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
                    <th>Update</th>
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
                    <td><a href="BillCtrl?service=update&oID=<%=rs.getString(1)%>">Update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
