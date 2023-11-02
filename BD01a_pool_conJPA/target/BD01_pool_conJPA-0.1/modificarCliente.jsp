<%-- 
    Document   : index
    Created on : 13-abr-2018, 11:29:48
    Author     : uzffs
--%>


<%@page import="demo.bd.Cliente"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@page import="demo.bd.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    ClienteDAO dao = null;
    Context ctx = new InitialContext();
    dao = (ClienteDAO)ctx.lookup("java:global/BD01_pool_conJPA/ClienteDAO");
    
    Long id = Long.valueOf(request.getParameter("id"));
    Cliente cliente= dao.find(id);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poooool CON JPA</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css"> 
    </head>
    <body>
        <h1>Poooool CON JPA!!! -> Modificar Cliente </h1>
        <br>
        <br><a href="index.jsp">Volver </a>
        <br>
        <br>
        <br>
        
        <table>
        <form method="POST" action="modificarCliente">
        <input type = "hidden" name="id" value="<%=cliente.getId()%>">
            <tr>
                <td>Id:</td>
                <td><b><%=cliente.getId()%></b></td>
            </tr>
            <tr>
                <td>Nombre:</td>
                <td><input name="nombre" value="<%=cliente.getNombre()%>"></td>
            </tr>            
            <tr>
                <td>Ap1:</td>
                <td><input name="ap1" value="<%=cliente.getAp1()%>"></td>
            </tr>
            <tr>
                <td>Saldo :</td>
                <td><input name="saldo" value="<%=cliente.getSaldo()%>"></td>            
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="Modificar"></td>
            </tr>
        </form>
        </table>
        
    </body>
</html>
