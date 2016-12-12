<%-- 
    Document   : overzicht
    Created on : 22-nov-2016, 11:27:47
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Overzicht!</h1>
        <table>
            <tr>
                <th>
                   Datum 
                </th>
                <th>
                   Bedrag 
                </th>
                <th>
                   Status 
                </th>
            </tr>
            <c:forEach var="onkost" items="${onkosten}">
            <tr>
                <td>
                    ${onkost.getDatum()}
                </td>
                <td>
                    ${onkost.getBedrag()}
                </td>
                <td>
                    ${onkost.getStatus()}
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
