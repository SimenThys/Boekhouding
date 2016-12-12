<%-- 
    Document   : keuze
    Created on : 12-dec-2016, 15:06:29
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>
            <c:forEach var="krediet" items="${sessionScope.Kredieten}">
                ${krediet.getLnr()} ${krediet.getLnaam()}
            </c:forEach>
        </p>
    </body>
</html>
