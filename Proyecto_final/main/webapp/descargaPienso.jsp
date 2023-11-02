<%-- 
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
        <meta http-equiv="refresh" content="300; url=operacionFinalizada.jsp">
        <title>Carga/Descarga Page</title>
    </head>
    <body>
        
        <div id="resultado">
            
        </div>
        <div id="minuto">

        </div>
        <div id="segundo">

        </div>
        <h1 align="center">Muelle descarga de Pienso</h1>

        <table align="center">
            <tr>         
                <td align="center">
                    <form method="POST" action=<%=response.encodeRedirectURL("addTransportista")%>>
                       <input type="hidden" name="paginaRetorno" value="descargaPienso.jsp"> 
                       <input type="hidden" name="opcion" value="Matricula registrada para descargar Pienso"> 
                       <fieldset>
                           <legend><b>Introduzca su matricula</b></legend>
                            <input name="matriculaEntrada">
                            <br>
                        </fieldset>
                       <br>
                        <button class="botones" valign="left" >Registrar matrícula</button> 
                    </form>
                </td>
                
                
                <td align="center">
                    <div id="matriculaIntroducida">
                        <%
                            String  matricula = "";
                            if (session.getAttribute("numeroMatricula") == null){
                                matricula = "SIN MATRICULA"; 
                            } else {
                                matricula = session.getAttribute("numeroMatricula").toString();
                            }

                            out.print(matricula); 
                        %>
                    </div>
                    <br><br>
                    <canvas id="graficoDescargaPienso" width="260" height="50" style="border:1px solid #d3d3d3;">
                        Your browser does not support the HTML5 canvas tag.
                    </canvas>             
                    
                    <div id="descargaPiensoOn">Esperando descarga</div>

                    <table><tr><td>Tiempo restante </td><td><div id="tempDescargaPienso" style="font-weight: bold"> --- </div></td><td> minutos </td></tr></table>

                </td>
            </tr>
        </table>
        
        <br><br>

        <table align="center">

            <tr>
                <td>
                    <form method="POST" action="<%=response.encodeRedirectURL("addTransportista")%>">
                        <input type="hidden" name="paginaRetorno" value="descargaPienso.jsp"> 
                        <input type="hidden" name="opcion" value="Inicio descarga Pienso">
                        <input type="hidden" name="matriculaEntrada" value=<%out.print(session.getAttribute("numeroMatricula"));%> >
                        <button class="botones" valign="left" id="iniciarDescargaPienso" onclick="descargarPienso();" >Descargar Pienso</button>
                    </form>
                </td>
                <td align="center">
                    <form method="POST" action=<%=response.encodeRedirectURL("addTransportista")%>>
                        <input type="hidden" name="paginaRetorno" value="descargaPienso.jsp"> 
                        <input type="hidden" name="opcion" value="Fin descarga Pienso"> 
                            <input type="hidden" name="matriculaEntrada" value=<%out.print(session.getAttribute("numeroMatricula"));%> >
                        <button class="botones"  valign="right" id="pararDescargaPienso" onclick="noDescargarPienso();">Finalizar descarga</button>
                    </form>
                </td>    
            </tr>
       </table> 
        <a href="muelles.jsp"> atrás </a>
        <br>
        <a href="index.jsp"> Inicio </a>
        <script type="text/javascript" src="websocketPienso.js"></script>
    </body>
</html>
