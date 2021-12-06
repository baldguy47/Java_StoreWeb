<%-- 
    Document   : ProductInsert
    Created on : Oct 7, 2021, 2:33:14 PM
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
            
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            
        %>
        
        <form action="ProductCtrl?service=insert" method="POST">
            <table border="0">
                <tr>
                    <td>Product ID</td>
                    <td><input type = "text" name = "pid" value =""/></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type = "text" name = "name" value =""/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type = "text" name = "quantity" value =""/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type = "text" name = "price" value =""/></td>
                </tr>
                <tr>
                    <td>Image</td>
                    <td><input type="file" id="img" name="Image" accept="image/*"></td>
                </tr>

                <tr>
                    <td>Description</td>
                    <td><input type = "text" name = "description" value =""/></td>
                </tr>
                
                <tr>
                    <td>Category ID</td>
                    <td>
                        <select name="cate" size="1">
                            <%while(rsCate.next()){%>
                            <option value = "<%=rsCate.getString(1)%>"><%=rsCate.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type = "submit" name = "submit" value ="Add"/></td>
                    <td><input type = "Reset" value ="Reset"/></td>
                </tr>
            </table>

        </form>
    </body>
</html>
