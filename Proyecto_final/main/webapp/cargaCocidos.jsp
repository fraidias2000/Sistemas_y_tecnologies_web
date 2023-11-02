<%-- 
  @author Alvaro Fraidias NIP 780336
 @author Rafael Rodriguez NIP 564786
  
 Referencias utilizadas:
 demos de @fserna
--%>

<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="gesiavsa.bd.Transportista"%>
<%@page import="javax.naming.Context"%>
<%@page import="gesiavsa.bd.TransportistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

   <% 
    TransportistaDAO dao = null;
    Context ctx = new InitialContext();
    dao = (TransportistaDAO)ctx.lookup("java:global/gesiavsa/TransportistaDAO");
    
    List<Transportista> cc= dao.findAll();
    
    String matriculaPienso = "error";
    String matriculaGallinas = "error";
    String matriculaFrescos = "error";
    String matriculaCocidos = "error";
%> 
<!DOCTYPE html>
<html>
    
    <style>
        .botones{
            border: none;
            color:white;
            background: #C23E00;
            padding: 30px 50px;
            size: 12px;
        }
        
        body {
            background-color: #FFBD45;
        }
    </style>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="55; url=operacionFinalizada.jsp">
        <title>Carga Cocidos Page</title>
    </head>
    <body>
        
        <div id="resultado">
            
        </div>
        <div id="minuto">

        </div>
        <div id="segundo">

        </div>
        <h1 align="center">Muelle de carga de huevos Cocidos</h1>
        
        <table align="center">
            <tr> 
                <td align="center">
                    <form method="POST" action="<%=response.encodeRedirectURL("addTransportista")%>">
                        <input type="hidden" name="paginaRetorno" value="cargaCocidos.jsp"> 
                        <input type="hidden" name="opcion" value="Matricula registrada para cargar huevos Cocidos"> 
                        <input type="hidden" name="paginaRetorno" value="cargaCocidos.jsp">
                        <fieldset>
                           <legend><b>Introduzca su matricula</b></legend>
                            <input name="matriculaEntrada">
                            <br>
                        </fieldset>
                        <br>                                               
                        <button class="botones" valign="left">Registrar matrícula</button> 
                    </form>
                </td>
                
                
                <td align="center">
                    
                    <%
                            String  matricula = "";
                            if (session.getAttribute("numeroMatricula") == null){
                                matricula = "SIN MATRICULA"; 
                            } else {
                                matricula = session.getAttribute("numeroMatricula").toString();
                            }

                            out.print(matricula); 
                        %>
                    <br><br>
                    <canvas id="graficoCargaCocidos" width="260" height="50" style="border:1px solid #d3d3d3;">
                        Your browser does not support the HTML5 canvas tag.
                    </canvas>        
                    
                    <br>
                    
                    <div id="cargaCocidosOn">Esperando carga</div>

                    <table><tr><td>Tiempo restante </td><td><div id="tempCargaCocidos" style="font-weight: bold"> --- </div></td><td> minutos </td></tr></table>
                </td>
            </tr>                
        </table>
                       
        <br><br><br>
                       
        <table align="center">
            <tr>
                <td align="center">
                    <form method="POST" action="<%=response.encodeRedirectURL("addTransportista")%>">
                       <input type="hidden" name="paginaRetorno" value="cargaCocidos.jsp"> 
                       <input type="hidden" name="opcion" value="Inicio carga huevos Cocidos">
                       <input type="hidden" name="matriculaEntrada" value=<%out.print(session.getAttribute("numeroMatricula"));%> >
                       
                       <button class="botones" valing="left" id="iniciarCargaCocidos" onclick="cargarCocidos();">Iniciar Carga</button> 
                    </form>
                </td>
            
                <td align="center">
                    <form method="POST" action="<%=response.encodeRedirectURL("addTransportista")%>">
                        <input type="hidden" name="paginaRetorno" value="cargaCocidos.jsp"> 
                        <input type="hidden" name="opcion" value="Fin carga huevos Cocidos"> 
                        <input type="hidden" name="matriculaEntrada" value=<%out.print(session.getAttribute("numeroMatricula"));%> >
                    <button class="botones" valign="right" id="pararCargaCocidos" onclick="noDescargarCocidos();">Finalizar Carga</button>
                    </form>
                </td>
            </tr>
        </table>                 
        <a href="muelles.jsp"> atrás </a>
        <br>
        <a href="index.jsp"> Inicio </a>
        <script type="text/javascript" src="websocketCocidos.js"></script>
    </body>
</html>
