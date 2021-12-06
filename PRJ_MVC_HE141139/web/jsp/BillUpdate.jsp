<%-- 
    Document   : BillUpdate
    Created on : Oct 14, 2021, 12:53:58 PM
    Author     : I'mTrung
--%>

<%@page import="model.DAOBill"%>
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
        
        <%
            ResultSet rs = (ResultSet) request.getAttribute("billUpdate");
        %> 
        <form action="./BillCtrl?service=update" method="POST">
            <table border="0">
                <%if (rs.next()) {%>

                <tr>
                    <th>Bill ID</th>
                    <th><input type = "text" name = "oID" value ="<%=rs.getString(1)%>"/></th>
                </tr>
                <tr>
                    <th>Customer name</th>
                    <th><input type="text" name="name" value="<%=rs.getString(3)%>" />

                    </th>

                </tr>


                <tr>
                    <th>Phone</th>
                    <th><input type = "text" name = "phone" value ="<%=rs.getString(4)%>"/></th>
                </tr>
                <tr>
                    <th>Address</th>
                    <th><input type = "text" name = "address" value ="<%=rs.getString(5)%>"/></th>
                </tr>
                
                <%}%>
                <tr>
                    <td><input type = "submit" name = "submit" value ="Update"/></td>
                    <td><input type = "Reset"  value ="Reset"/></td>
                </tr>
            </table>

        </form>
    </body>
</html>
