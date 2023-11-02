<%-- 
    Document   : index
    Created on : 1 mar. 2022, 18:20:45
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ASumar!!!</h1>
        <hr>
        <br>
        <br>
        <form method="POST" action="<%=response.encodeURL("sumar")%>">
            1er Sumando: <input name="s1" value="${s1}">
            <br>
            2º Sumando: <input name="s2" value="${s2}">
            <br>
            <input type="submit" value="¡A Sumar!">
        </form>
            
        El resultado es: ${suma}
        <br>
        Duración: ${ns} ns
        <br>
    </body>
</html>
