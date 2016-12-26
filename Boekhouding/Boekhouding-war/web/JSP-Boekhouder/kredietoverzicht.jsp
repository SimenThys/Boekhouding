<%-- 
    Document   : kredietoverzicht
    Created on : 24-dec-2016, 15:54:23
    Author     : Ben
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
        <h1>Overzicht van krediet nummer ${krediet.getKnr()}</h1>  
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>onr</th>
                    <th>Bedrag</th>
                    <th>Status</th>
                    <th>Bekijken</th>
                </tr>
                <c:forEach var="onkost" items="${onkosten}">
                    <tr>
                        <td>${onkost.getOnr()}</td>
                        <td>${onkost.getBedrag()}</td>
                        <td>${onkost.getStatus()}</td>
                        <td>
                            <form method="post" action="<c:url value='/ResController.do' />">
                                <input type="hidden" name="vorig" value="${vorig}"/>
                                <input type="hidden" name="ganaar" value="overzicht_status"/>
                                <input type="hidden" name="vraagonkostop" value="${onkost.getOnr()}"/>
                                <button type="submit" class="btn btn-primary">Bekijk <span class="glyphicon glyphicon-search"></span></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="2">knr</th>
                    <th colspan="2">Saldo</th>
                </tr>
                <tr>
                    <th colspan="2">${krediet.getKnr()}</th>
                    <th colspan="2">${krediet.getSaldo()}</th>
                </tr>
            </tbody>
        </table>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="boekhouder_krediet"/>
            <input type="hidden" name="vorig" value="${vorig}"/>
            <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
        </form>
    </body>
</html>
