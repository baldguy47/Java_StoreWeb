<%-- 
    Document   : Head
    Created on : Oct 30, 2021, 5:07:21 PM
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
        <div style="width: 40%; float: left">
            Logo
            <label style="margin-left: 60% "><b>Ngô Quang Trung HE141139</b></label>
        </div>
        <div style="width: 60%; float: left; text-align: right">

            <% String logged = (String) session.getAttribute("login");
                String admin = (String) session.getAttribute("admin");

                //admin logged
                if (admin != null) {
                    session.removeAttribute("login");
                    out.println("<label style=\"margin-left: 10%;margin-right:50% \"><b>Welcome " + admin + " admin!</b></label>");
                    out.println("<a href=\"LoginCtrl?service=logout\">Logout</a>");
                } else {
                    //user logged

                    if (logged != null) {
                        session.removeAttribute("admin");
                        out.println("<label style=\"margin-right:50% \"><b>Welcome " + logged + " user!</b></label>");
                        out.println(" <a href=\"/PRJ_MVC_HE141139/MainCtrl\">Home</a> ");

                        out.println("<a href=\"/PRJ_MVC_HE141139/LoginCtrl?service=logout\">Logout</a> ");

                        out.println("<a href=\"/PRJ_MVC_HE141139/BillCtrl?service=delivering\">Your order</a> ");
                    } else {
                        out.println("<a href=\"./LoginCtrl?service=register\">Register</a> "
                                + "<a href=\"./LoginCtrl\">Login</a> ");
                    }
                    //general function user
                    out.println("<a href=\"./ProductCtrl?service=showCart\">Cart</a> ");
                }


            %>


        </div>
    </body>
</html>
