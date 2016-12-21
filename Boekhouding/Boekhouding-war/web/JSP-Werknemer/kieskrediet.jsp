<%-- 
    Document   : kieskrediet
    Created on : 13-dec-2016, 21:00:30
    Author     : Simen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kies Krediet</title>
    </head>
    <body>
        <h1>Kies een krediet om de onkost mee te betalen!</h1>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="krediet_overzicht"/>
            <input type="hidden" name="onr" value="${onr}"/>
            <input type="hidden" name="bedrag" value="${bedrag}"/>
            <input type="hidden" name="omschr" value="${omschr}"/>
            <select name="knr">
                <c:forEach var="krediet" items="${gewoonk}">
                    <option value="${krediet.getKnr()}">${krediet.getKnr()} - (SALDO: ${krediet.getSaldo()})</option>
                </c:forEach>
                <c:forEach var="krediet" items="${ondernulk}">
                    <option style="background-color: red" value="${krediet.getKnr()}">${krediet.getKnr()} - (SALDO: ${krediet.getSaldo()})</option>
                </c:forEach>
            </select>
            <input type="submit" name="keuze" value="Vorige"/>
            <input type="submit" name="keuze" value="Bevestigen"/>
        </form>
    </body>
</html>
