<%-- 
    Document   : index
    Created on : 13-abr-2018, 11:29:48
    Author     : uzffs
--%>

<%@page import="demo.bd.Cliente"%>
<%@page import="demo.bd.BBDD"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    BBDD bd = BBDD.getInstance();
    List<Cliente> cc = bd.getAllClientes();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>01b. Poooool SIN JPA</title>
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css"> 
    </head>
    <body>
        <h1>01b. Poooool SIN JPA!!! </h1>
        
        <div style="background-color: red; color: white">${msg}</div>
        <table>
            <form method="POST" action="<%=response.encodeURL("addCliente")%>">
            <tr>
                <td>Nombre:</td>
                <td><input name="nombre"></td>
            </tr>
            <tr>
                <td>Ap1:</td>
                <td><input name="ap1"></td>
            </tr>
            <tr>
                <td>Saldo :</td>
                <td><input name="saldo"></td>            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Añadir (no Funciona)"></td>
            </tr>
        </form>
        </table>
        <br>
        <br>
        <br>
        <table border="1">
            <tr style="background-color: blue; color:white">
                <td>Id</td>
                <td>Nombre</td>
                <td>Ap1</td>
                <td>Saldo</td>
                <td>Borrar</td>
            </tr>
        <% for (Cliente c: cc) { %>
        <tr>
            <td><%=c.getId()%></td>
            <td><%=c.getNombre()%></td>
            <td><%=c.getAp1()%></td>
            <td align="right"><%=c.getSaldo()%></td>
            <td><a href="<%=response.encodeURL("eliminarCliente?id="+c.getId())%>">Eliminar (No Funciona)</td>
        </tr>
        <% } %>
        </table>
    </body>
</html>
