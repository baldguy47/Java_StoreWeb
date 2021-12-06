<%-- 
    Document   : showCart
    Created on : Oct 15, 2021, 8:29:11 PM
    Author     : I'mTrung
--%>

<%@page import="entity.Item"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="width: 100%; height: 100px">
            <jsp:include page="../../Head.jsp"></jsp:include>
            </div>

            <div style="margin-left: 20%; margin-right: auto">
                <form action="ProductCtrl?service=updated" method="post" >
                    <table border="1" style="text-align: center">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            String mess="";
                            if (request.getAttribute("alert") != null) {
                                mess = (String) request.getAttribute("alert");

                            }
                            double total = 0;
                            java.util.Enumeration em = session.getAttributeNames();
                            int increase = 0;
                            while (em.hasMoreElements()) {

                                String id = em.nextElement().toString();
                                if (!id.equals("login") && !id.equals("admin")) {
                                    Item item = (Item) session.getAttribute(id);
                                    Product pro = item.getProduct();
                                    total += pro.getPrice() * item.getQuantity();
                                    increase++;

                        %>
                        <tr>

                            <td><%=pro.getPname()%></td>
                            <td><input type="number" style="vertical-align: middle;-moz-appearance: textfield;text-align: center" size="3" name="quantity<%=increase%>" value="<%=item.getQuantity()%>" min="1" max="<%=pro.getQuantity()%>" required/></td>
                            <td><%=pro.getPrice()%></td>
                            <td><%=pro.getPrice() * item.getQuantity()%></td>
                            <td><a href="/PRJ_MVC_HE141139/RemoveCtrl?pid=<%=pro.getPid()%>">Remove</a></td>
                        </tr>
                        <%      }
                            }
                        %>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><b>Sum</b></td>
                            <td><b><%=total%></b></td>
                        </tr>


                    </tbody>
                </table>
                <input type="submit" name="submit" value="Update"/>
            </form>        

            <h4><a href="/PRJ_MVC_HE141139/RemoveCtrl?service=RemoveAll">Remove All</a></h4>  
            <h2><a href="/PRJ_MVC_HE141139/MainCtrl">Continue Shopping</a></h2>
            <br>
            <h2><a href="/PRJ_MVC_HE141139/ProductCtrl?service=checkout&total=<%=total%>">Check-out</a></h2>
            <%=mess%>
        </div>

    </body>

</html>
