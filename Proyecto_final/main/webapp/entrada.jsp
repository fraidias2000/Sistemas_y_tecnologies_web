<%-- 
  @author Alvaro Fraidias NIP 780336
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="gesiavsa.bd.Transportista"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="gesiavsa.bd.TransportistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



   <% 
    TransportistaDAO dao = null;
    Context ctx = new InitialContext();
    dao = (TransportistaDAO)ctx.lookup("java:global/gesiavsa/TransportistaDAO");
    
    List<Transportista> todosLosCamiones = dao.findAll();
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
        for (int i = 0; i < camionesDentroEmpresa.size() && camionesDentroEmpresa != null; i++) {
            Transportista t2 = camionesDentroEmpresa.get(i);
            if(t1.getNumeroMatricula().equals(t2.getNumeroMatricula())){
                camionesDentroEmpresa.remove(i);
                
            }
        }
    } else if(diaRegistro == diaActual){
        camionesDentroEmpresa.add(t1);
    }
}

%> 
    
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
        <title>Entrada Page</title>
    </head>
    <body>
        <h1 align="center">Página de entrada</h1>
        <br><br>          
         <table align="center">
            <tr>
                <td>
                    <form method="POST" action=<%=response.encodeRedirectURL("addTransportista")%>>
                    <fieldset align="left">
                            <legend><b>Por favor, indíque el motivo de su visita:</b></legend>
                            <br>               
                            <label>
                                <input type="radio" name="opcion" value="Descargar pienso"> Descargar pienso
                            </label>
                            <br><br>
                            <label>
                                <input type="radio" name="opcion" value="Descargar gallinas"> Descargar gallinas
                            </label>
                            <br><br>
                            <label>
                                <input type="radio" name="opcion" value="Retirar huevos frescos"> Retirar huevos frescos
                            </label>
                            <br><br>
                            <label>
                                <input type="radio" name="opcion" value="Retirar huevos cocidos"> Retirar huevos cocidos
                            </label>
                            <br><br>
                        </fieldset>
                        <br><br>
                        <fieldset>
                            <legend><b>Introduzca su matricula</b></legend>
                            <input name="matriculaEntrada">
                        <br><br>
                        </fieldset>
                        <br>
                        <input type="submit" value="Registrar entrada">
                        <input type="hidden" name="paginaRetorno" value="confirmacionEntrada.jsp"> 
                    </form>
                </td>
                
                <td>
                    <fieldset>
                        <legend><b>Tabla de registro Gesiavsa</b></legend>
                        <br>
                        <table border="1">
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
            </tr>
        </table>

        <br>
        <a href="index.jsp"> Inicio </a>
        
    </body>
    
</html>
