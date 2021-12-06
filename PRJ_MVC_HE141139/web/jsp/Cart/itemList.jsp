<%-- 
    Document   : itemList
    Created on : Oct 15, 2021, 7:51:26 PM
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
    <%ResultSet rs = (ResultSet) request.getAttribute("ketqua");
        String title = (String) request.getAttribute("title");%>
        <%String mess = (String) session.getAttribute("login");
            if (mess != null) {
                out.println("<h3> Welcome "+mess+"</h3>");
            }%>
    <table border="1">
        <caption><%=title%></caption>
        <thead>
            <tr>
                <th>PID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Category</th>
                <th></th>
            </tr>
        </thead>

        <tbody>
            <%
                while (rs.next()) {%>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getInt(3)%></td>
                <td><%=rs.getDouble(4)%></td>
                <td><img src="./img/<%=rs.getString(5)%>" width="100px" height="150px"></td>
                <td><%=rs.getString(6)%></td>
                <td><%=rs.getString(8)%></td>
                <td><a href="ProductCtrl?service=addingItemtoCart&pid=<%=rs.getString(1)%>">Add to cart</a></td>
            </tr>
            <%}%>
        </tbody>
    </table>
    <br>
    <br>
</body>
</html>
