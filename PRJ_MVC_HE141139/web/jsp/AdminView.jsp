<%-- 
    Document   : AdminView
    Created on : Oct 10, 2021, 3:40:58 PM
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
            <div  style="margin:auto; width: 50%">
                <a href="AdminCtrl?service=addAdmin">Add Admin</a> |
                <a href="AdminCtrl?service=Product">Product Management</a> |
                <a href="CustomerCtrl">Customer Management</a> |
                <a href="BillCtrl">Bill Management</a>
            </div>
            <div style="margin-left: auto; margin-right: auto;width:50%">
                
            
            <%
                ResultSet rs = (ResultSet) request.getAttribute("ketqua");
                String title = (String) request.getAttribute("title");
            %>
            


            <table border="1" style="margin-top: 1%">
                <caption><%=title%></caption>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Delete</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(2)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><a href="AdminCtrl?service=delete&id=<%=rs.getString(1)%>">delete</a></td>

                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
