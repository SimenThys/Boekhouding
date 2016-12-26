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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="css/algemeen.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Boekhouder! Welkom, uw klantennr is ${sessionScope.wnr}</h1>
        <form method="post" action="<c:url value='/ResController.do' />">
            <div style="margin: 20px;">
                <input type="radio" name="ganaar" value="werknemer_overzicht"> Eigen onkost indienen<br>
                <c:if test="${type==1}">
                    <input type="hidden" name="vorig" value="boek"/>
                    <input type="radio" name="ganaar" value="boekhouder_krediet"> Kredieten bekijken<br>
                </c:if> 
                <c:if test="${type==2}">
                    <input type="hidden" name="vorig" value="man"/>
                    <input type="radio" name="ganaar" value="manager_goedkeuren"> Onkosten Goedkeuren<br>
                </c:if>
                <c:if test="${type ==3}">
                    <input type="hidden" name="vorig" value="beide"/>
                    <input type="radio" name="ganaar" value="beide_boek"> Kredieten bekijken<br>
                    <input type="radio" name="ganaar" value="beide_man"> Onkosten Goedkeuren<br>
                </c:if>   
            </div>
            <input class="btn btn-primary" type="submit" value="verder">
        </form>
    </body>
</html>
