<%-- 
    Document   : login
    Created on : 22-nov-2016, 11:26:15
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/algemeen.css">
        <title>Welkom!</title>
    </head>
    <body>
        <h1>Geef uw gegevens om in te loggen</h1>
        <form method="post" action="j_security_check">
            Personeelsnummer: <input type="text" name="j_username" required="" ><br>
            Wachtwoord: <input type="password" name="j_password" required="" >
            <input type="submit" value="Volgend">
        </form>
    </body>
</html>
