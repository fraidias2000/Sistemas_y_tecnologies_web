<%-- 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
  https://stackoverflow.com/questions/33228029/response-sendredirect-in-jsp
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <style>
        .botones{
            border: none;
            color:white;
            background: #C23E00;
            padding: 30px 50px;
        }
        
        body {
            background-color: #FFBD45;
        }
    </style>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="8; url=index.jsp">
        <title>Confirmación Entrada</title>
    </head>
    <body align="center">        
        <%
            String zona = "";

            String opcion = (String)session.getAttribute("opcion");
            String numeroMatricula = (String)session.getAttribute("numeroMatricula");
//            Calendar fecha = Calendar.getInstance();
            String hora = session.getAttribute("hora").toString();
            //Date fecha2 = new Date(fecha);

            switch (opcion){
                case "Descargar pienso":
                    zona = "descargaPienso.jpg";
                    break;
                case "Descargar gallinas":
                    zona = "descargaGallinas.jpg";
                    break;
                case "Retirar huevos frescos":
                    zona = "cargaFrescos.jpg";
                    break;
                case "Retirar huevos cocidos":
                    zona = "cargaCocidos.jpg";
                    break;
                default:
                    zona = "salida.jpg";
                    break;
            }
        %>
        <h1>Entrada registrada correctamente</h1>
        <br>
        <table align="center">            
            <tr>
                <td>
                    <h2>El camión con matrícula</h2>
                        <br>
                        <h1> <%out.println(numeroMatricula);%></h1>
                        <br>
                        <h2>se ha registrado a las</h2>
                        <br>
                        <h1> <%out.println(hora);%> </h1>
                        <br>
                        <h2>para</h2>
                        <br>
                        <h1> <%out.println(opcion);%> </h1>
                        <br>
                        <h2>Por favor, diríjase a la zona indicada</h2>
                </td>
                <td>
                    .                                                     .
                </td>
                <td>
                    <img width="70%" heigth="70%" src="images/<%out.println(zona);%>">
                </td>
            </tr>
        </table>
    </body>
    
</html>
