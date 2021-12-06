<%-- 
    Document   : ProductView
    Created on : Oct 6, 2021, 8:35:11 AM
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
            ResultSet rs = (ResultSet) request.getAttribute("ketqua");
            String mess = (String)request.getAttribute("quantitymess");
            if(mess==null){
                mess=" ";
            }
            out.println(mess);
        %> 

        
        <table border="1" style="justify-content: center;align-items: center; text-align: center ">

            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>

                    <th>Category</th>

                </tr>
            </thead>

            <tbody>
                <%                    while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getInt(3)%></td>
                    <td><%=rs.getDouble(4)%></td>
                    <td><img src="<%=rs.getString(5)%>" width="100px" height="150px"></td>
                    <td><%=rs.getString(6)%></td>

                    <td><%=rs.getString(8)%></td>
                    <td><a href="ProductCtrl?service=addingItemtoCart&pid=<%=rs.getString(1)%>">Add to cart</a></td>


                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <br>
        <a href="MENU.html">BACK TO MENU</a>
    </body>
    
</html>
