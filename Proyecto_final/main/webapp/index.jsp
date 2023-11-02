<!--
 @author Alvaro Fraidias NIP 780336
 @author Rafael Rodriguez NIP 564786
 
 Referencias utilizadas:
 apuntes de @fserna  
 -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="gesiavsa.rest.TemperaturaOW"%>
<% 
    TemperaturaOW temperaturaOW = new TemperaturaOW();
    temperaturaOW.getTemperaturaOW();
%>

<!DOCTYPE html>
<html>
    
    <style>
        .botones{
            border: none;
            color:white;
            background: #C23E00;
            padding: 30px 50px;
            size: 12px;
        }
        
        body {
            background-color: #FFBD45;
        }
    </style>
    
    <head>
        <title>GESIAVSA WELCOME PAGE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
      <center>
        <h1>Bienvenido a Integraciones Avícolas Valencianas S.A.</h1>
        
        <H2>El tiempo en Utiel para hoy es <%out.println( temperaturaOW.getTempOW());%>ºC</H2>

        <br>
        
        <img src="images/granjaExt.jpg" width="1080" height="500">
        
        <br><br>
        <table>
                <td>
                    <a href="entrada.jsp">                     
                        <button type="submit" class="botones">Entrada</button> 
                    </a> 
                </td>
                <td align="center">
                    <a href="muelles.jsp">
                        <button type="submit" class="botones">Cargar / Descargar </button> 
                    </a>                    
                </td>
                <td>
                    <a href="salida.jsp">                     
                        <button type="submit" class="botones">Salida</button> 
                    </a> 
                </td>
            </tr>            
        </table>
      </center>
    </body>
</html>



