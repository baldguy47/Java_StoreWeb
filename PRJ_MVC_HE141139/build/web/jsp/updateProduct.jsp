<%-- 
    Document   : updateProduct
    Created on : Oct 7, 2021, 1:31:28 PM
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
        <a href=\"LoginCtrl?service=logout\">Logout</a>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            
        %>
        <%if(rs.next()){%>
        <form action="ProductCtrl?service=update" method="POST">
            <table border="0">
                <tr>
                    <td>Product ID</td>
                    <td><input type = "text" name = "pid" value ="<%=rs.getString(1)%>"/></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type = "text" name = "name" value ="<%=rs.getString(2)%>"/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type = "text" name = "quantity" value ="<%=rs.getInt(3)%>"/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type = "text" name = "price" value ="<%=rs.getDouble(4)%>"/></td>
                </tr>
                <tr>
                    <td>Image</td>
                    <td><input type = "text" name = "Image" value ="<%=rs.getString(5)%>"/></td>
                </tr>

                <tr>
                    <td>Description</td>
                    <td><input type = "text" name = "description" value ="<%=rs.getString(6)%>"/></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type = "radio" name = "status" value ="1" <%=(rs.getInt(7)==1?"checked":"")%>/>Enable</td>
                    <td><input type = "radio" name = "status" value ="0" <%=(rs.getInt(7)==0?"checked":"")%>/>Disable</td>
                </tr>
                                <%}%>

                <tr>
                    <td>Category ID</td>
                    <td>
                        <select name="cate" size="1">
                            <%while(rsCate.next()){%>
                            <option value = "<%=rsCate.getString(1)%>" <%=(rsCate.getInt(1) == rs.getInt(8) ? " selected" : "") %>><%=rsCate.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type = "submit" name = "submit" value ="Update"/></td>
                    <td><input type = "Reset" value ="Reset"/></td>
                </tr>
            </table>

        </form>
        
    </body>
</html>
