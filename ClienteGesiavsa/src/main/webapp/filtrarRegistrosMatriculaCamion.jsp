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
        <%
           String matriculaCamion = request.getParameter("matriculaCamion");
            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaRegistro = null;
            int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);           
            int diaRegistro = 0;
        %>
        <h1>Bienvenido al panel de control de Integraciones Avícolas Valencianas S.A.</h1>
        <img src="images/granjaExt.jpg" width="720" height="250">
        <br><br><br>
        <h2>Filtro para el camion con matricula: <%=matriculaCamion%></h2>
        <button onclick="window.location.href='index.jsp'"class="botones">Volver Inicio</button>
        <br><br>
         <table border="1">
            <tr style="background-color:blue; color:white">
                 <td>Hora Registro</td>
                <td>Matricula</td>
                <td>Opcion</td>               
            </tr>
            <% for (Transportista t: transportistas) {
                    if(t.getNumeroMatricula() != null && !t.getNumeroMatricula().equals("")){
                        fechaRegistro = t.getHoraRegistro();
                        diaRegistro = fechaRegistro.get(Calendar.DAY_OF_MONTH);
                        if( matriculaCamion.equals(t.getNumeroMatricula()) && 
                            t.getNumeroMatricula() != null && 
                            !t.getNumeroMatricula().equals("")){%>
                                <tr>
                                    <td><%=t.parsearFecha()%></td>
                                    <td><%=t.getNumeroMatricula()%></td>
                                    <td><%=t.getOpcion()%></td>                       
                                </tr>
                       <%}
                    } 
                }%>
        
        </table>    
    </body>
    </center>
</html>

