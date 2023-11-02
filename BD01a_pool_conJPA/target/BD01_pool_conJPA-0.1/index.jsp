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
    
    List<Cliente> cc= dao.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>01. BD Poooool CON JPA</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css"> 
    </head>
    <body>
        <h1>01. BD Poooool CON JPA!!! </h1>
        
        <table>
        <form method="POST" action="addCliente">
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
                <td><input name="saldo"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Añadir"></td>
            </tr>
        </form>
        </table>
        <br>
        <br>
        <br>
        <table border="1">
            <tr style="background-color:blue; color:white">
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
            <td><a href="modificarCliente.jsp?id=<%=c.getId()%>">Modificar</td>
        </tr>
        <% } %>
        </table>
        <br>
        <br>
        * He quitado la opción "eliminar", porque colisiona con el OneToMany 
        de la demo-03.
    </body>
</html>
