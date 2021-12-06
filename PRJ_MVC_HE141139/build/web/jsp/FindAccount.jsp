<%-- 
    Document   : CustomerFindAccount
    Created on : Oct 9, 2021, 8:07:39 PM
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
        <% 
            String alert = (String) request.getAttribute("alert");
            if(alert == null){
                alert = "";
            }
        %> 
        <form action="LoginCtrl?service=changePassword" method="POST">
            <table border="0">
               
                <tr>
                    <td>Username</td>
                    <td><input type = "text" name = "user" value =""/></td>
                </tr>
                
                <tr>
                    <td><input type = "submit" name = "changeSubmit" value ="Find Your Account"/></td>
                    <td><input type = "Reset" name = "reset" value ="Reset"/></td>
                </tr>
            </table>

        </form>
        <p><%=alert%></p>
    </body>
</html>
