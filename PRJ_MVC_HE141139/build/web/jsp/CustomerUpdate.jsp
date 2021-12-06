<%-- 
    Document   : CustomerUpdate
    Created on : Oct 9, 2021, 7:08:47 PM
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
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            if (rs.next()) {
        %>
        <form action="CustomerCtrl?service=update" method="POST">
            <table border="0">
                <tr>
                    <td>Customer ID</td>
                    <td><input type = "text" name = "cid" value ="<%=rs.getString(1)%>" readonly/></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type = "text" name = "name" value ="<%=rs.getString(2)%>"/></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type = "text" name = "phone" value ="<%=rs.getString(3)%>"/></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type = "text" name = "address" value ="<%=rs.getString(4)%>"/></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type = "text" name = "user" value ="<%=rs.getString(5)%>"/></td>
                </tr>

                <tr>
                    <td>Status</td>
                    <td><input type = "radio" name = "status" value ="1" <%=(rs.getInt(7) == 1 ? " checked" : "")%>/>Enable</td>
                    <td><input type = "radio" name = "status" value ="0" <%=(rs.getInt(7) == 0 ? " checked" : "")%>/>Disable</td>
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
