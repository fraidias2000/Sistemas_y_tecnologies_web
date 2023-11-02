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
    String idConocido = request.getParameter("id");
    
    AgendaClient rest = new AgendaClient();
    Conocido c = rest.find(Conocido.class, idConocido);
    rest.close();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda web bclient</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <h1>Agenda web Client</h1>
        <hr>
        <table align="center">
            <tr>
                
                <td valign="top">
              
                    <a href="index.jsp">Volver</a>
                    <br><br>                      

                    <form method="POST" action="editConocido">
                        <input type="hidden" name="id" value="<%=c.getId()%>">
                        <table>
                            <tr>
                                <td>Nombre:</td>
                                <td><input name="nombre" value="<%=c.getNombre()%>"></td>
                            </tr>
                            <tr>
                                <td>Ap1</td>
                                <td><input name="ap1" value="<%=c.getAp1()%>"></td>
                            </tr>
                            <tr>
                                <td>Telefono</td>
                                <td><input name="tfno" value="<%=c.getTelefono()%>"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Modificar"></td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
        
    </body>
</html>
