/**
 * @author Rafael Rodriguez NIP 564786
 * 
 * Referencias utilizadas:
 * demos de @fserna
 * https://parzibyte.me/blog/2019/02/14/peticion-http-get-java-consumir-html-json/
 * https://mvnrepository.com/artifact/org.json/json/20180813
 * https://parzibyte.me/blog/2019/02/16/parsear-decodificar-json-java-jsonobject-jsonarray/
 * https://github.com/stleary/JSON-java
 */


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gesiavsa.rest;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

//OW es de OpenWeather
public class TemperaturaOW {
    
    final String URL = "https://api.openweathermap.org/data/2.5/weather?q=utiel&appid=996866bb954a2a8e65b9644c759a5b85";  
    final double KELVIN = 273;
    
    //JSONObject myObject = new JSONObject();
    double tempOW = 0;
    
    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
            // Esto es lo que vamos a devolver
            StringBuilder resultado = new StringBuilder();
            // Crear un objeto de tipo URL
            URL url = new URL(urlParaVisitar);

            // Abrir la conexión e indicar que será de tipo GET
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            // Búferes para leer
            BufferedReader rd = new BufferedReader
                        (new InputStreamReader(conexion.getInputStream()));
            String linea;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                    resultado.append(linea);
            }
            // Cerrar el BufferedReader
            rd.close();
            // Regresar resultado, pero como cadena, no como StringBuilder
            return resultado.toString();
    }
        
    public double getTemperaturaOW() {
        String respuesta = "";
        String temperatura = "493";
        
        try {
            respuesta = peticionHttpGet(URL);
            if(respuesta != null){
                JSONObject lecturaOW = new JSONObject(respuesta);
                temperatura = lecturaOW.query("/main/temp_max").toString();
                tempOW = (Double.parseDouble(temperatura.substring(0,3)) - KELVIN);
            }else{
                tempOW = 0.0;
            }
              
        } catch (Exception e) {
                // Manejar excepción
                e.printStackTrace();
        }
        
        return tempOW;
	}
    
    public double getTempOW(){
        return tempOW;
    }
}
