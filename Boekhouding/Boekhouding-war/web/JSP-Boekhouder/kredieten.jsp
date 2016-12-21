<%-- 
    Document   : keuze
    Created on : 12-dec-2016, 15:06:29
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tbody>
                <tr><th>knr</th><th>Saldo</th><th>Type: 1=gewaarborgd</th></tr>
                <c:forEach var="krediet" items="${kredieten}">
                    <tr><td>${krediet.getKnr()}</td><td>${krediet.getSaldo()}</td><td>${krediet.getTyp()}</td></tr>
                </c:forEach>
            </tbody>
        </table>            
    </body>
</html>
