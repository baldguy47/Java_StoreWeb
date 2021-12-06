<%-- 
    Document   : CustomerAdd
    Created on : Oct 9, 2021, 5:31:49 PM
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
        <form action="CustomerCtrl?service=addCustomer" method="POST">
            <table border="0">
                <tr>
                    <td>Name</td>
                    <td><input type = "text" name = "name" value =""/></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type = "text" name = "phone" value =""/></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type = "text" name = "address" value =""/></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type = "text" name = "user" value =""/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type = "password" name = "password" value =""/></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type = "radio" name = "status" value ="1" checked/>Enable</td>
                    <td><input type = "radio" name = "status" value ="0"/>Disable</td>
                </tr>
                <tr>
                    <td><input type = "submit" name = "submit" value ="Add"/></td>
                    <td><input type = "Reset" name = "reset" value ="Reset"/></td>
                </tr>
            </table>

        </form>
    </body>
</html>
