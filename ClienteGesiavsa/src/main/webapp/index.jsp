<!--
 * @author Alvaro Fraidias NIP 780336
 * 
 * Referencias utilizadas:
 * apuntes de @fserna
 * 
 */
 -->

<%@page import="java.util.Calendar"%>
<%@page import="administradorGesiavsa.bd.Transportista"%>
<%@page import="administradorGesiavsa.rest.client.TransportistaClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
        .botones{
            border: none;
            color:white;
            background: #0618FF;
            padding: 30px 40px;
        }
        
        body {
            background-color: #91F3D8;
        }
    </style>

 <% 
    TransportistaClient rest = new TransportistaClient();
    Transportista[] transportistas = rest.findAll(Transportista[].class);
    rest.close();    
%>
    
<!DOCTYPE html>
<html>
    <center>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Integraciones Avícolas Valencianas S.A.</title>
    </head>
    <body>        
        <h1>Bienvenido al panel de control de Integraciones Avícolas Valencianas S.A.</h1>    
        <img src="images/granjaExt.jpg" width="720" height="250">
        <h2>Estos son todos los registros:</h2>
        <br><br>   
        <button onclick="window.location.href='filtrarRegistrosDiaAnterior.jsp?filtro=1'" class="botones">Filtrar día anterior</button>
            <button onclick="window.location.href='filtrarRegistros.jsp?filtro=5'"class="botones">Filtrar 5 días</button>
            <button onclick="window.location.href='filtrarRegistros.jsp?filtro=10'"class="botones">Filtrar 10 días</button>
            <br><br>
            <form action="filtrarRegistrosMatriculaCamion.jsp" method="post">
                <label for="inputText">Filtrar por matricula de camión</label>
                <input type="text" id="matriculaCamion" name="matriculaCamion">
                <button type="submit"class="botones">Filtrar</button>
            </form>
            
         <table border="1">
            <tr style="background-color:blue; color:white">
                 <td>Hora Registro</td>
                <td>Matricula</td>
                <td>Opcion</td>               
            </tr>
            <% for (Transportista t: transportistas) {
                    if(t.getNumeroMatricula() != null && !t.getNumeroMatricula().equals("")){%>         
                <tr>
                    <td><%=t.parsearFecha()%></td>
                    <td><%=t.getNumeroMatricula()%></td>
                    <td><%=t.getOpcion()%></td>                       
                </tr>
                <% }
                } %>
        
            
        </table> 
        
        
    </body>
    </center>
</html>