<%-- 
    Document   : index
    Created on : 25 feb. 2020, 18:20:36
    Author     : Usuario
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
  List<String> frutasFavoritasSesion = (List<String>)session.getAttribute("frutas");  
  if (frutasFavoritasSesion==null){
      frutasFavoritasSesion = new ArrayList();
      session.setAttribute("frutas", frutasFavoritasSesion); //Añadimos el arreglo a la sesion local
  }
  

  List<String> frutasFavoritasGlobal = (List<String>)application.getAttribute("frutasGlobal");  
  if (frutasFavoritasGlobal==null){
      frutasFavoritasGlobal = new ArrayList();
      application.setAttribute("frutasGlobal", frutasFavoritasGlobal);//Añadimos el arreglo a la sesion global
  }
  

  String nombre = (String)session.getAttribute("nombre");
if (nombre==null){
%>
    <jsp:forward page="index.jsp"/>
<%
}

List<String> nombresConectados = (List<String>)application.getAttribute("usuariosConectados");
if (nombresConectados==null){
    nombresConectados = new ArrayList();
    application.setAttribute("usuariosConectados", nombresConectados);
}
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>01b JSPs y scopes</title>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td><h1>01b JSPs y scopes</h1></td>

                <td  align="right">
                    <div align="right">
                        Hola, <b>${nombre}</b>
                        <br>
                        IP: <%=request.getRemoteAddr()%>
                        <br>
                        <a href="logout">Logout</a>
                    </div>
                </td>
                
            </tr>
        </table>
        
        <hr>
        
        <br>
        <br>
        <table width="100%">
            <tr>
                <td>
            <fieldset>
                <legend>Session:</legend>
                <form method="POST" action="<%=response.encodeRedirectURL("addFrutaFavoritaSession")%>">
                        ¿Cuál es tu fruta favorita? <input name="fruta"> 
                        <input type="submit" value="Enviar">
                    </form>
                <ul>
                <% for(String f:frutasFavoritasSesion){ %>
                    <li><%=f%></li>
                <% } %>
                </ul>
            </fieldset>
                </td>
                
                
                <td>
                    <fieldset>
                        <legend>Servlet Context:</legend>
                            <form method="POST" action="<%=response.encodeRedirectURL("addFrutaFavoritaGlobal")%>">
                                ¿Cuál es tu fruta favorita? <input name="fruta"> 
                                <input type="submit" value="Enviar">
                            </form>
                        <ul>
                <% for(String fg: frutasFavoritasGlobal){ %>
                    <li><%=fg%></li>
                <% } %>
                </ul>
                    </fieldset>
                </td>
                
                <td>
                    <fieldset>
                        <legend>Usuarios conectados:</legend>
                        <ul>
                            <% for (String n: nombresConectados){%>
                            <li><%=n%></li>
                            <% } %>
                        </ul>
                    </fieldset>
                </td>
            </tr>
        </table>
        
    </body>
</html>
