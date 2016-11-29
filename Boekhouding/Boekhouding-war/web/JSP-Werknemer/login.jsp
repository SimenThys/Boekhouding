<%-- 
    Document   : login
    Created on : 22-nov-2016, 11:26:15
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welkom!</title>
    </head>
    <body>
        <jsp:include page="fragment.jspf"/>
        <h1>Geef uw gegevens om in te loggen</h1>
        <form method="post" action="<c:url value="CarControllerURL"/>">
            <input type="hidden" name="goto" value="reserveer.jsp">
            Personeelsnummer: <input type="text" name="klantnr"><br>
            Wachtwoord: <input type="password" name="password">
            <input type="submit" value="Volgend">
        </form>
    </body>
</html>
