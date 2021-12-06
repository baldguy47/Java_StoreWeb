<%-- 
    Document   : AdminChangeAccount
    Created on : Oct 10, 2021, 4:34:40 PM
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
            ResultSet rs = (ResultSet) request.getAttribute("rsAdmin");
            if (rs.next()) {
        %>
        <form action="LoginCtrl?service=adminApproved&id=<%=rs.getString(1)%>" method="POST">
            <table border="0">
                
                <tr>
                    <td>Username</td>
                    <td><input type = "text" name = "user" value ="<%=rs.getString(2)%>" readonly/></td>
                </tr>
                <tr>
                    <td>New Password</td>
                    <td><input type = "password" name = "password" pattern=".{8,16}" title="Mật khẩu yêu cầu 8-16 ký tự" required value =""/></td>
                </tr>

                
                <tr>
                    <td><input type = "submit" name = "submit" value ="Update"/></td>
                    <td><input type = "Reset" name = "reset" value ="Reset"/></td>
                </tr>
            </table>
            <%}%>
        </form>
    </body>
</html>
