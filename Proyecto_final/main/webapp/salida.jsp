<%-- 
  @author Alvaro Fraidias NIP 780336
  
 Referencias utilizadas:
 demos de @fserna
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.Context"%>
<%@page import="gesiavsa.bd.TransportistaDAO"%>
<%@page import="gesiavsa.bd.Transportista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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


<%   
TransportistaDAO dao = null;
Context ctx = new InitialContext();
dao = (TransportistaDAO)ctx.lookup("java:global/gesiavsa/TransportistaDAO");

List<Transportista> todosLosCamiones= dao.findAll();
ArrayList<Transportista> camionesDentroEmpresa = new ArrayList<>();

 Calendar fechaRegistro = null;
     Calendar fechaActual = Calendar.getInstance();
     int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
     int diaRegistro = 0;

//Rellena un nuevo array con los camiones que todavía están dentro de la empresa
for (Transportista t1: todosLosCamiones) {
    fechaRegistro = t1.getHoraRegistro();
    diaRegistro = fechaRegistro.get(Calendar.DAY_OF_MONTH);
    if(t1.getOpcion().equals("Ha abandonado las instalaciones")) {
        for (int i = 0; i < camionesDentroEmpresa.size(); i++) {
            Transportista t2 = camionesDentroEmpresa.get(i);
            if(t1.getNumeroMatricula().equals(t2.getNumeroMatricula())){
                camionesDentroEmpresa.remove(i);
            }
        }
    } else if (!t1.getOpcion().equals("Inicio descarga Pienso") &&
               !t1.getOpcion().equals("Inicio descarga gallinas") &&
               !t1.getOpcion().equals("Inicio carga huevos Frescos") &&
               !t1.getOpcion().equals("Inicio carga huevos Cocidos") &&
               !t1.getOpcion().equals("Fin descarga Pienso") &&
               !t1.getOpcion().equals("Fin descarga gallinas") &&
               !t1.getOpcion().equals("Fin carga huevos Frescos") &&
               !t1.getOpcion().equals("Fin carga huevos Cocidos") &&
               !t1.getOpcion().equals("Matricula registrada para cargar huevos Cocidos")&&
               !t1.getOpcion().equals("Matricula registrada para cargar huevos Frescos")&&
               !t1.getOpcion().equals("Matricula registrada para descargar Pienso")&&
               !t1.getOpcion().equals("Matricula registrada para descargar Gallinas")&&
               diaRegistro == diaActual){
        camionesDentroEmpresa.add(t1);
    }
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SALIDA Page</title>
</head>
<body align="center">
    <h1>Indique su matrícula para abandonar las instalaciones</h1>
    <br>
    <table align="center">
        <tr>
            <td align="left">
                <fieldset align="center" border="black">
                    <legend><h2><b>Camiones en las instalaciones</b></h2></legend>
                    <table align="center" border="1">
                        <tr style="background-color:blue; color:white">
                            <td>Matricula</td>
                            <td>Opcion</td>    
                             <td>Hora Registro</td>
                        </tr>
                        <% for (Transportista tra: camionesDentroEmpresa) { %>
                        <tr>
                            <td><%=tra.getNumeroMatricula()%></td>
                            <td><%=tra.getOpcion()%></td>
                            <td><%=tra.parsearFecha()%></td>
                        </tr>
                        <% } %>  
                    </table>
                </fieldset>
            </td>
            <td></td>
            <td align="right">                
                <form method="POST" action=<%=response.encodeRedirectURL("salidaTransportista")%>>
                    <input type="hidden" name="paginaRetorno" value="salida.jsp">
                    <input type="hidden" name="opcion" value="Ha abandonado las instalaciones">
                    
                    Matricula camion:
                        <input name="matriculaEntrada">
                        <br><br>
                        <!--<td colspan="2" align="center">-->
                        <input class="botones" type="submit" value="Abandonar instalaciones">
                </form>
            </td>
        </tr>
       <tr>
            <td align="left"><a href="index.jsp"> Inicio </a></td>
            <td><br><h2>Gracias por su visita.</h2></td>
            <td></td>
        </tr>
    </table>
<br>

</body>
</html>