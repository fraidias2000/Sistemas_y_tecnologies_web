<%-- 
    Document   : index
    Created on : 24 mar. 2022, 13:48:38
    Author     : fsern
--%>

<%@page import="demo.Conocido"%>
<%@page import="demo.rest.client.AgendaClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    AgendaClient rest = new AgendaClient();
    Conocido[] conocidos = rest.findAll(Conocido[].class);
    rest.close();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda web REST client</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <h1>Agenda web REST Client</h1>
        <hr>
        <b>IMPORTANTE</b>: Edita el fichero "demo/rest/client/AgendaCliente.java" y asegúrate de que la variable "BASE_URI" apunta a la URL del servicio web 
        que quieres utilizar ("localhost/xxx" o "155.210.71.106/xxx")
        <br>
        <br>
        <table align="center">
            <tr>
                <td valign="top">
                    <table>
                        <tr style="background-color: blue;color:white;"><td>id</td><td>Nombre</td><td>Ap1</td><td>Teléfono</td><td width="25px;"></td><td></td><td></td></tr>
                    <% for ( Conocido c: conocidos) { %>
                    <tr><td><%=c.getId()%></td><td><%=c.getNombre()%></td><td><%=c.getAp1()%></td><td><%=c.getTelefono()%></td><td></td><td><a href="editConocido.jsp?id=<%=c.getId()%>">Modificar</a></td><td><a href="deleteConocido?id=<%=c.getId()%>">Borrar</a></td></tr>
                    <% } %>
                    </table>
                </td>
                
                <td width="75px;"></td>
                
                
                <td valign="top">
                    <big>Nuevo contacto:</big>
                    <form method="POST" action="addConocido">
                        <table>
                            <tr>
                                <td>Nombre:</td>
                                <td><input name="nombre"></td>
                            </tr>
                            <tr>
                                <td>Ap1</td>
                                <td><input name="ap1"></td>
                            </tr>
                            <tr>
                                <td>Telefono</td>
                                <td><input name="tfno"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Guardar"></td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
        
    </body>
</html>
