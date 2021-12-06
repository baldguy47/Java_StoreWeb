<%-- 
    Document   : Admin_ProductView
    Created on : Oct 24, 2021, 4:26:52 PM
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
            <div style="width: 30%; float: left">
            <%
                ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            %> 
            <ul style="list-style-type: none">
                <li><a href="AdminCtrl?service=Product">All</a></li>
                    <%while (rsCate.next()) {%>
                <li><a href="AdminCtrl?service=viewCate&cateID=<%=rsCate.getInt(1)%>">
                        <%=rsCate.getString(2)%></a></li>
                        <%}%>
            </ul>
        </div>

        <div  style="margin-left: 30%; margin-right: auto ">
            <a href="AdminCtrl?service=addAdmin">Add Admin</a> |
            <a href="AdminCtrl?service=Product">Product Management</a> |
            <a href="CustomerCtrl">Customer Management</a> |
            <a href="BillCtrl">Bill Management</a> |
            <a href="CategoryCtrl">Category Management</a>
            <%
                String adminLogin = (String) session.getAttribute("admin");
                if (adminLogin != null) {
                    out.println("| <a href=\"ProductCtrl?service=insert\">Add Product</a><br>");
                }
            %>
        </div>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ketqua");
            String title = (String) request.getAttribute("title");

        %> 

        <table border="1" style="margin-left: 30%; margin-right: auto; margin-top:1%">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>PID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category</th>
                    <th>Update</th>
                    <th>Delete</th>
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
                    <td><img src="<%=rs.getString(5)%>" width="100px" height="150px"></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                    <td><%=rs.getString(8)%></td>
                    <td><a href="ProductCtrl?service=update&pid=<%=rs.getString(1)%>">update</a></td>
                    <td><a href="ProductCtrl?service=delete&pid=<%=rs.getString(1)%>">delete</a></td>

                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>
        <a href="MENU.html">BACK TO MENU</a>
    </body>
</html>
