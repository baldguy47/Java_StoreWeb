<%-- 
    Document   : index
    Created on : Oct 30, 2021, 5:06:40 PM
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
        <div style="width: 100%; height: 100px">
            <jsp:include page="Head.jsp"></jsp:include>
            </div>
            <div style="width: 30%; float: left">
            <jsp:include page="Category.jsp"></jsp:include>
            </div>
            <div style="width: 70%; float: left">
            <jsp:include page="/jsp/ProductView.jsp"></jsp:include>
        </div>

    </body>
</html>
