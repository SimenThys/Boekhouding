<%-- 
    Document   : status
    Created on : 22-nov-2016, 11:28:27
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
        <h1>Status van onkost nr. ${gevraagdeonkost.get(0).getOnr()}</h1>
        <c:choose>
            <%-- Wanneer status in aanmaak is en de werknemer deze zelf heeft aangemaakt: toon formulier om aan te passen. --%>
            <c:when test="${gevraagdeonkost.get(0).getStatus() == 0 and 
                            gevraagdeonkost.get(0).getWnr().getWnr()== wnr}">
                <form  method="post" action="<c:url value='/ResController.do' />">
                    <input type="hidden" name="ganaar" value="status_keuze"/>
                    <table>
                        <tr>
                            <td>
                                Onkostnr.
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getOnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kredietnr.
                            </td>
                            <td>
                                <%-- Nog niet gekozen, pas in volgende stap --%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Werknemernr.
                            </td>
                            <td>
                                ${wnr}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Datum ingediend
                            </td>
                            <td>
                                <%-- Ligt nog niet vast, pas na indienen --%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Bedrag
                            </td>
                            <td>
                                €<input type="text" name="onr" value="${gevraagdeonkost.get(0).getBedrag()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Status
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getNaamStatus()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Omschrijving
                            </td>
                            <td>
                                <textarea name="onr"/>${gevraagdeonkost.get(0).getOmschrijving()}</textarea>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" name="keuze" value="Tijdelijk opslaan"/>
                    <input type="submit" name="keuze" value="Doorsturen"/>
                </form>
            </c:when>
            <%-- Anders: toon gegevens --%>
            <c:otherwise>
                <form  method="post" action="<c:url value='/ResController.do' />">
                    <input type="hidden" name="ganaar" value="status_overzicht"/>
                    <table>
                        <tr>
                            <td>
                                Onkostnr.
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getOnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kredietnr.
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getKnr().getKnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Ingediend door werknemer nr.
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getWnr().getWnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Datum ingediend
                            </td>
                            <td>
                                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${gevraagdeonkost.get(0).getDatum()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Bedrag
                            </td>
                            <td>
                                €${gevraagdeonkost.get(0).getBedrag()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Status
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getNaamStatus()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Omschrijving
                            </td>
                            <td>
                                ${gevraagdeonkost.get(0).getOmschrijving()}
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Vorige"/>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
