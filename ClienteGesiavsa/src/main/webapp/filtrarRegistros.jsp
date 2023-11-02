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
            int filtro = Integer.parseInt(request.getParameter("filtro"));
            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaRegistro = null;
            int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
            int mesActual = fechaActual.get(Calendar.MONTH) + 1; 
            int anyoActual = fechaActual.get(Calendar.YEAR);            
            int diaRegistro = 0;       
            int mesRegistro = 0;
            int diaActualTexto = diaActual;
            int mesActualTexto = mesActual;
            int anyoActualTexto = anyoActual;
            
            if(diaActualTexto <= filtro){
                if(mesActual == 1){
                    anyoActual--;
                }
               diaActualTexto = filtro - diaActual;
               mesActualTexto--;
               switch(mesActual){
               
                    case 1:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 2:
                         diaActualTexto = 28 - diaActualTexto;
                    break;
                    case 3:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 4:
                         diaActualTexto = 30 - diaActualTexto;
                    break;
                    case 5:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 6:
                         diaActualTexto = 30 - diaActualTexto;
                    break;
                    case 7:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 8:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 9:
                         diaActualTexto = 30 - diaActualTexto;
                    break;
                    case 10:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                    case 11:
                         diaActualTexto = 30 - diaActualTexto;
                    break;
                    case 12:
                         diaActualTexto = 31 - diaActualTexto;
                    break;
                }
            }else{
            diaActualTexto = diaActual - filtro;
            }
            String fechaActualTexto = diaActualTexto + "/" + mesActualTexto + "/" + anyoActualTexto ;
        %>
        <h1>Bienvenido al panel de control de Integraciones Avícolas Valencianas S.A.</h1>
        <img src="images/granjaExt.jpg" width="720" height="250">
        <br><br><br>
        <h2>Camiones que hubo desde el día: <%=fechaActualTexto%></h2>
        <button onclick="window.location.href='index.jsp'"class="botones">Volver Inicio</button>
        <br><br>
         <table border="1">
            <tr style="background-color:blue; color:white">
                 <td>Hora Registro</td>
                <td>Matricula</td>
                <td>Opcion</td>               
            </tr>
            <% int i = 0;
           for (Transportista t: transportistas) { 
           i++;
             fechaRegistro = t.getHoraRegistro();
             diaRegistro = fechaRegistro.get(Calendar.DAY_OF_MONTH);
             mesRegistro = fechaRegistro.get(Calendar.MONTH) + 1;
            if((diaRegistro >= (diaActual-filtro)) && (mesActualTexto ==  mesRegistro)){%>
        <tr>
            <td><%=t.parsearFecha()%></td>
            <td><%=t.getNumeroMatricula()%></td>
            <td><%=t.getOpcion()%></td>                       
        </tr>
        <% }} %>
        
        </table>    
    </body>
    </center>
</html>