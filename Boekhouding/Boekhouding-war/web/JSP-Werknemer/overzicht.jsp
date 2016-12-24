<%-- 
    Document   : overzicht
    Created on : 22-nov-2016, 11:27:47
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
        <h1>Overzicht!</h1>
        <table class="table table-striped">
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
                <th>
                   Bekijken/Bewerken
                </th>
                <th>
                   Verwijderen
                </th>
            </tr>
            <c:forEach var="onkost" items="${onkosten}">
            <tr>
                <td>
                    <fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${onkost.getDatum()}"/>
                </td>
                <td>
                    €${onkost.getBedrag()}
                </td>
                <td>
                    ${onkost.getNaamStatus()}
                </td>
                <td>
                    <form method="post" action="<c:url value='/ResController.do' />">
                        <input type="hidden" name="ganaar" value="overzicht_status"/>
                        <input type="hidden" name="vraagonkostop" value="${onkost.getOnr()}"/>
                        <button type="submit" class="btn btn-primary">Bekijk <span class="glyphicon glyphicon-search"></span></button>
                    </form>
                </td>
                <td>
                    <c:if test="${onkost.getStatus()==0}">
                    <form method="post" action="<c:url value='/ResController.do' />">
                        <input type="hidden" name="ganaar" value="overzicht_overzicht"/>
                        <input type="hidden" name="verwijder" value="${onkost.getOnr()}"/>
                        <button type="submit" class="btn btn-danger">Verwijder <span class="glyphicon glyphicon-remove"></span></button>
                    </form>
                    </c:if>    
                </td>
            </tr>
            </c:forEach>
        </table>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="overzicht_nieuw"/>
            <button type="submit" class="btn btn-primary">Nieuwe Onkost <span class="glyphicon glyphicon-file"></span></button>
        </form>
    </body>
</html>
