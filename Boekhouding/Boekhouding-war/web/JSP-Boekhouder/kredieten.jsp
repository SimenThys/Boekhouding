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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="css/algemeen.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Kredieten</h1>
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>knr</th>
                    <th>Saldo</th>
                    <th>Type: 1=gewaarborgd</th>
                    <th>Bekijken</th>
                </tr>
                <c:forEach var="krediet" items="${kredieten}">
                    <tr>
                        <td>${krediet.getKnr()}</td>
                        <td>${krediet.getSaldo()}</td>
                        <td>${krediet.getTyp()}</td>
                        <td>
                            <form method="post" action="<c:url value='/ResController.do' />">
                                <input type="hidden" name="vorig" value="boek"/>
                                <input type="hidden" name="ganaar" value="kredieten_status"/>
                                <input type="hidden" name="vraagkredietop" value="${krediet.getKnr()}"/>
                                <button type="submit" class="btn btn-primary">Bekijk <span class="glyphicon glyphicon-search"></span></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="boekhouder_overzicht"/>
            <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
        </form>
    </body>
</html>
