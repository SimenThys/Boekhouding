<%-- 
    Document   : overzicht
    Created on : 22-nov-2016, 11:27:47
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Overzicht!</h1>
        <table>
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
                    <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${onkost.getDatum()}"/>
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
                        <button type="submit">Bekijken</button>
                    </form>
                </td>
                <td>
                    <c:if test="${onkost.getStatus()==0}">
                    <form method="post" action="<c:url value='/ResController.do' />">
                        <input type="hidden" name="ganaar" value="overzicht_overzicht"/>
                        <input type="hidden" name="verwijder" value="${onkost.getOnr()}"/>
                        <button type="submit">Verwijderen</button>
                    </form>
                    </c:if>    
                </td>
            </tr>
            </c:forEach>
        </table>
        <form method="post" action="<c:url value='/ResController.do' />">
            <input type="hidden" name="ganaar" value="overzicht_nieuw"/>
            <button type="submit">Nieuwe Onkost</button>
        </form>
    </body>
</html>
