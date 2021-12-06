<%-- 
    Document   : Category
    Created on : Oct 30, 2021, 5:09:15 PM
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
        <ul style="list-style-type: none">
            <li><a href="MainCtrl">All</a></li>
            <%while (rsCate.next()) {%>
            <li><a href="MainCtrl?go=viewCate&cateID=<%=rsCate.getInt(1)%>">
                    <%=rsCate.getString(2)%></a></li>
                    <%}%>
        </ul>
    </body>
</html>
