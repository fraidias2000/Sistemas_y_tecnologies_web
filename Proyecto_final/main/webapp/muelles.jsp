<!--
 @author Rafael Rodriguez NIP 564786
 
 Referencias utilizadas:
 apuntes de @fserna  
 -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .botones{
            border: none;
            color:white;
            background: #C23E00;
            padding: 100px 100px;
        }
        
        body {
            background-color: #FFBD45;
        }
    </style>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muelles de carga y de descarga</title>
    </head>
    <body>
        <h1 align="center">Seleccione la zona donde va a operar</h1>
        <br>
           
        <br>
        <table  align="center">
            <tr>
                <td width="75" height="75" style="background-color: #FFBD45">
                    <a href="descargaPienso.jsp">
                        <button type="submit" class="botones">Descargar Pienso </button> 
                    </a>
                    
                </td>
                <td width="75" height="75" style="background-color: #FFBD45">
                    <a href="descargaGallinas.jsp">
                        <button type="submit" class="botones">Descargar Gallinas</button> 
                    </a>
                    
                </td>
            </tr>
            <tr>
                <td width="75" height="75" style="background-color: #FFBD45">
                    <a href="cargaFrescos.jsp">
                        <button type="submit" class="botones">Cargar Huevos Frescos </button> 
                    </a>
                    
                </td>
                <td width="75" height="75" style="background-color: #FFBD45">
                    
                    <a href="cargaCocidos.jsp">
                        <button type="submit" class="botones">Cargar Huevos Cocidos </button> 
                    </a>
                </td>
            </tr>
            
            
        </table>


        <br>
        <a href="index.jsp"> Inicio </a>        
        
    </body>
</html>
