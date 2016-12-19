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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="css/algemeen.css" rel="stylesheet">
        <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
        <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
	<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Status van onkost nr. ${gevraagdeonkost.getOnr()}</h1>
        <c:choose>
            <%-- Wanneer status in aanmaak is en de werknemer deze zelf heeft aangemaakt: toon formulier om aan te passen. --%>
            <c:when test="${gevraagdeonkost.getStatus() == 0 and 
                            gevraagdeonkost.getWnr().getWnr()== wnr}">
                    <form  method="post" action="<c:url value='/ResController.do' />">
                        <input type="hidden" name="ganaar" value="status_keuze"/>
                        <input type="hidden" name="isnieuw" value="${isnieuw}"/>
                        <table class="table table-striped">
                            <tr>
                                <td>
                                    Onkostnr.
                                </td>
                                <td>
                                    ${gevraagdeonkost.getOnr()}
                                    <input type="hidden" name="onr" value="${gevraagdeonkost.getOnr()}"/>
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
                                    <div class="container">
                                        <div class="row">
                                            <div class='col-sm-6'>
                                                <div class="form-group">
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='text' class="form-control" />
                                                        <span class="input-group-addon">Kalender 
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <script type="text/javascript">
                                                $(function () {
                                                    $('#datetimepicker1').datetimepicker({
                    defaultDate: "11/1/2013",
                    disabledDates: [
                        moment("12/25/2013"),
                        new Date(2013, 11 - 1, 21),
                        "11/22/2013 00:53"
                    ]
                });
                                                });
                                            </script>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Bedrag
                                </td>
                                <td>
                                    €<input type="text" name="bedrag" value="${gevraagdeonkost.getBedrag()}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Status
                                </td>
                                <td>
                                    ${gevraagdeonkost.getNaamStatus()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Omschrijving
                                </td>
                                <td>
                                    <textarea name="omschr"/>${gevraagdeonkost.getOmschrijving()}</textarea>
                                </td>
                            </tr>
                        </table>
                        <button class="btn btn-primary" type="submit" name="keuze" value="Vorige"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
                        <button class="btn btn-success" type="submit" name="keuze" value="Tijdelijk opslaan">Tijdelijk opslaan <span class="glyphicon glyphicon-floppy-disk"></span></button>
                        <button class="btn btn-warning"type="submit" name="keuze" value="Doorsturen">Doorsturen <span class="glyphicon glyphicon-arrow-right"></span></button>
                    </form>
            </c:when>
            <%-- Anders: toon gegevens --%>
            <c:otherwise>
                <form  method="post" action="<c:url value='/ResController.do' />">
                    <input type="hidden" name="ganaar" value="status_overzicht"/>
                    <table class="table table-striped">
                        <tr>
                            <td>
                                Onkostnr.
                            </td>
                            <td>
                                ${gevraagdeonkost.getOnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Kredietnr.
                            </td>
                            <td>
                                ${gevraagdeonkost.getKnr().getKnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Ingediend door werknemer nr.
                            </td>
                            <td>
                                ${gevraagdeonkost.getWnr().getWnr()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Datum ingediend
                            </td>
                            <td>
                                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${gevraagdeonkost.getDatum()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Bedrag
                            </td>
                            <td>
                                €${gevraagdeonkost.getBedrag()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Status
                            </td>
                            <td>
                                ${gevraagdeonkost.getNaamStatus()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Omschrijving
                            </td>
                            <td>
                                ${gevraagdeonkost.getOmschrijving()}
                            </td>
                        </tr>
                    </table>
                    <button class="btn btn-primary" type="submit" name="keuze" value="Vorige"><span class="glyphicon glyphicon-arrow-left"></span> Vorige</button>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
