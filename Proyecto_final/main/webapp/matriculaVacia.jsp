<%-- 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
  https://stackoverflow.com/questions/33228029/response-sendredirect-in-jsp
  https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGyZVEwyLNS3mLuIkZvVRiBmXFusj0nBFJKg&usqp=CAU
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta http-equiv="refresh" content="5; url=muelles.jsp">
        <title>Confirmación Entrada</title>
    </head>
    <body align="center">
        <br><br>
        
        <img src="images/error.jpg" width="400" height="350">
        
        <br><br>
        <h1>
            MATRICULA NO INTRODUCIDA
            <br><br><br>
            POR FAVOR
            <br><br><br>
            INTENTELO DE NUEVO
        </h1>
    </body>
    
</html>
