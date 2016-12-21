<%-- 
    Document   : keuze-Boekhouder
    Created on : 22-nov-2016, 11:36:39
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
        <h1>Boekhouder! Welkom, uw klantennr is ${sessionScope.nummer}</h1>
        <form method="post" action="<c:url value='/ResController.do' />">
            <!--<input type="hidden" value="boekhouder_keuze" name="ganaar"/>-->
            <input type="radio" name="ganaar" value="werknemer_overzicht">Eigen onkost indienen<br>
            <input type="radio" name="ganaar" value="boekhouder_krediet">Kredieten bekijken<br>
            <input type="submit" value="Reserveren">
        </form>
    </body>
</html>
