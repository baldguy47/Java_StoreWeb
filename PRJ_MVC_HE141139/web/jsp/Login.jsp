<%-- 
    Document   : Login
    Created on : Oct 19, 2021, 8:20:14 PM
    Author     : I'mTrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <%
            String message = (String) request.getAttribute("mess");
        %>
        
        <form action="LoginCtrl?service=login" method = "post">
            username: <input type="text" name="username"/><br>
            password: <input type="password" name="password"/><br>
            <input type="submit" value="login"/><br>
            <%=message%>
        </form>
        <a href="LoginCtrl?service=changePassword">Forgot your password?</a>
        
    </body>
</html>
