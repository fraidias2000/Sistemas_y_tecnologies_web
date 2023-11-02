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
            int diaActualTexto = diaActual;
            int mesActualTexto = mesActual;
            int anyoActualTexto = anyoActual;
            diaActualTexto =  diaActual - 1  ;
            if(diaActualTexto == 0){
                if(mesActual == 1){
                    anyoActual--;
                }
               
               switch(mesActual){
               case 1:
                    diaActualTexto = 31;
               break;
               case 2:
                    diaActualTexto = 28;
               break;
               case 3:
                    diaActualTexto = 31;
               break;
               case 4:
                    diaActualTexto = 30;
               break;
               case 5:
                    diaActualTexto = 31;
               break;
               case 6:
                    diaActualTexto = 30;
               break;
               case 7:
                    diaActualTexto = 31;
               break;
               case 8:
                    diaActualTexto = 31;
               break;
               case 9:
                    diaActualTexto = 30;
               break;
               case 10:
                    diaActualTexto = 31;
               break;
               case 11:
                    diaActualTexto = 30;
               break;
               case 12:
                    diaActualTexto = 31;
               break;
            }
            }
            String fechaActualTexto = diaActualTexto + "/" + mesActualTexto + "/" + anyoActualTexto ;          
        %>
        <h1>Bienvenido al panel de control de Integraciones Avícolas Valencianas S.A.</h1>
        <img src="images/granjaExt.jpg" width="720" height="250">
        <br><br><br>
        <h2>Camiones que hubo el día: <%=fechaActualTexto%></h2>
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
                        if(diaRegistro == (diaActual-filtro)){%>
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