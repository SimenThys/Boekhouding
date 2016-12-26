<%-- 
    Document   : kieskrediet
    Created on : 13-dec-2016, 21:00:30
    Author     : Simen
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
        <title>Kies Krediet</title>
    </head>
    <body>
        <h1>Kies een krediet om de onkost mee te betalen!</h1>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="krediet_overzicht"/>
            <input type="hidden" name="onr" value="${onr}"/>
            <input type="hidden" name="bedrag" value="${bedrag}"/>
            <input type="hidden" name="omschr" value="${omschr}"/>
            <input type="hidden" name="datum" value="${datum}"/>
            <table class="table table-striped">
                <tr>
                    <th>Kredietnummer </th>
                    <th>Saldo</th>
                    <th>Selecteer krediet</th>
                </tr>   
                <c:forEach var="krediet" items="${gewoonk}">
                    <tr>
                        <td>${krediet.getKnr()}</td>
                        <td>${krediet.getSaldo()}</td>
                        <td><input type="radio" name="krediet" value="${krediet.getKnr()}"></td>
                    </tr>
                </c:forEach>
                <c:forEach var="krediet" items="${ondernulk}">
                    <tr style="background-color: red">
                        <td>${krediet.getKnr()}</td>
                        <td>${krediet.getSaldo()}</td>
                        <td><input type="radio" name="krediet" value="${krediet.getKnr()}"></td>
                    </tr>
                </c:forEach>
            </table>
            <button class="btn btn-primary" type="submit" name="keuze" value="Vorige"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
            <button class="btn btn-success" type="submit" name="keuze" value="Bevestig">Bevestigen <span class="glyphicon glyphicon-ok"></span></button>
        </form>
    </body>
</html>
