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
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<link rel="icon" href="favicon.ico">-->
        <link href="css/algemeen.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">\
        <link href="css/algemeen.css" rel="stylesheet">
        <title>Welkom!</title>
    </head>
    <body>
    
        <div class="container">

        <form class="form-signin" method="post" action="j_security_check">
            <h1>Geef uw gegevens om in te loggen</h1>
            <label for="inputNummer" class="sr-only">Personeelsnummer</label>
            <input type="text" id="inputNummer" name="j_username" class="form-control" placeholder="Personeelsnummer" required autofocus>
            <label for="inputWachtwoord" class="sr-only">Wachtwoord</label>
            <input type="password" id="inputWachtwoord" name="j_password" class="form-control" placeholder="Wachtwoord" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" value="Volgend">Sign in</button>
        </form>

        </div>
    </body>
</html>
