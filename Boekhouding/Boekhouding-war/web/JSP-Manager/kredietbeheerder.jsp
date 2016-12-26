<%-- 
    Document   : keuze-Manager
    Created on : 22-nov-2016, 11:37:00
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Manager!</h1>
        <table class="table table-striped">
            <tr>
                <th>Datum</th>
                <th>Bedrag</th>
                <th>Status</th>
                <th>Goedkeuren</th>
                <th>Afkeuren</th>
                <th>Bekijk onkost</th>
                <th>Bekijk krediet</th>
            </tr>
            <c:forEach var="onkost" items="${onkosten}">
                <tr>
                    <td><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${onkost.getDatum()}"/></td>
                    <td>€${onkost.getBedrag()}</td>
                    <td>${onkost.getNaamStatus()}</td>
                    <td>
                        <form method="post" action="<c:url value='/ResController.do' />">
                            <input type="hidden" name="ganaar" value="onkost_keuren"/>
                            <input type="hidden" name="onkost" value="${onkost.getOnr()}"/>
                            <input type="hidden" name="keuren" value="2"/>
                            <button type="submit" class="btn btn-success">Goedkeuren <span class="glyphicon glyphicon-ok"></span></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<c:url value='/ResController.do' />">
                            <input type="hidden" name="ganaar" value="onkost_keuren"/>
                            <input type="hidden" name="onkost" value="${onkost.getOnr()}"/>
                            <input type="hidden" name="keuren" value="3"/>
                            <button type="submit" class="btn btn-danger">Afkeuren <span class="glyphicon glyphicon-remove"></span></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<c:url value='/ResController.do' />">
                            <input type="hidden" name="ganaar" value="overzicht_status"/>
                            <input type="hidden" name="vorig" value="onkost"/>
                            <input type="hidden" name="vraagonkostop" value="${onkost.getOnr()}"/>
                            <button type="submit" class="btn btn-primary">Bekijk onkost <span class="glyphicon glyphicon-search"></span></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<c:url value='/ResController.do' />">
                            <input type="hidden" name="vorig" value="krediet"/>
                            <input type="hidden" name="ganaar" value="kredieten_status"/>
                            <input type="hidden" name="vraagkredietop" value="${onkost.getOnr()}"/>
                            <button type="submit" class="btn btn-primary">Bekijk krediet <span class="glyphicon glyphicon-search"></span></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="boekhouder_overzicht"/>
            <button class="btn btn-primary" type="submit" name="keuze" value="Vorige"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
        </form>
    </body>
</html>
