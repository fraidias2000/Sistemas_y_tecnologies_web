<%-- 
    Document   : index
    Created on : 25-feb-2014, 11:57:46
    Author     : fserna
--%>

<%@page import="java.util.concurrent.Executors"%>
<%@page import="java.util.concurrent.ExecutorService"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Long t0Async = (Long)session.getAttribute("t0Async");
    Long t0Sync = (Long)session.getAttribute("t0Sync");
    
    Long t = System.currentTimeMillis(); 
    Long totalSync = 0L;
    Long totalAsync = 0L;
    if (t0Async!=null){
        totalAsync = t - t0Async;
    }
    if (t0Sync!=null){
        totalSync = t - t0Sync;
    }
    
    session.setAttribute("t0Async", null);
    session.setAttribute("t0Sync", null);

ExecutorService executor =  Executors.newFixedThreadPool(5);//creating a pool of 5 threads  

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo Servlet Síncrono-Asíncrono</title>
    </head>
    <body>
        <h1>Demo Servlet Síncrono-Asíncrono</h1>
        <hr>

        <table>
            <tr style="background-color: blue; color:white">
                <td></td>
                <td>Núm. Iteraciones</td>
                <td>ms en Servlet</td>
                <td>ms Respuesta</td>
            </tr>
            <tr>
                <td>Síncrono:</td>
                <td>
                    <form method="post" action="servletSincrono">
                        <input type="text" value="900000000" name="numVeces">
                        <input type="submit" value="Enviar">
                    </form>
                </td>
                <td>${msSync} ms</td>
                <td><%=totalSync%> ms</td>
            </tr>            
            <tr>
                <td>Asíncrono:</td>
                <td>
                    <form method="post" action="servletAsincrono">
                        <input type="text" value="900000000" name="numVeces">
                        <input type="submit" value="Enviar">
                    </form>
                </td>
                <td>${msAsync} ms</td>
                <td><%=totalAsync%> ms</td>
            </tr>
           
        </table>
        <br><br>
        Última actualización: <%=new Date().toString()%>
    </body>
</html>
