package stw.weatherr.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import stw.weatherr.dto.OpenWeatherDTO;



/**
 * Jersey REST client generated for REST resource:WeatherService [weather]<br>
 * USAGE:
 * <pre>
 *        OpenWeatherRESTClient client = new OpenWeatherRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fsernafortea
 */
public class OpenWeatherRESTClient {

    private WebTarget webTarget;
    private Client client;
    
   
    private String BASE_URI = "http://api.openweathermap.org/data/2.5"; 
    private String apiOpenWeatherToken;    // <<<=== YOU NEED TO WRITE HERE YOUR OPENWEATHER TOKEN!!!!
   
    // microprofile.config
    private Config config = ConfigProvider.getConfig();
    // OJO, cuando se usa "microprofile-config", los valores de BASE_URI y appID (token de acceso) 
    // pueden ser inicializados en: 
    //      - index.jsp, haciendo:
    //              System.setProperty("stw.openweatherRest.url", "http://api.openweathermap.org/data/2.5");
    //              System.setProperty("stw.openweatherRest.apiOpenWeatherToken", "XXXX");
    //
    //      - creando variables de entorno del SO, haciendo:
    //              "set stw.openweatherRest.url=http://api.openweathermap.org/data/2.5"
    //              "set stw.openweatherRest.apiOpenWeatherToken=XXXX"
    //              * pueden comprobarse los valores seteados haciendo: 
    //                  "echo %stw.openweatherRest.url%"
    //                  "echo %stw.openweatherRest.apiOpenWeatherToken%"
    //
    //      - creando las variables de entorno al construir la imagen docker: 
    //              ENV stw.openweatherRest.url=http://api.openweathermap.org/data/2.5
    //              ENV stw.openweatherRest.apiOpenWeatherToken=XXXX
    //
    //      - añadiendo 2 líneas al fichero "src/main/resources/META-INF/microprofile-config.properties"
    //              stw.openweatherRest.url=http://api.openweathermap.org/data/2.5
    //              stw.openweatherRest.apiOpenWeatherToken=XXXX



    public OpenWeatherRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        
        if (config!=null){
            BASE_URI = config.getValue("stw.openweatherRest.url", String.class);  
            apiOpenWeatherToken = config.getValue("stw.openweatherRest.apiOpenWeatherToken", String.class);  
        }
    }



      public OpenWeatherDTO getWeatherNowByCity(String _city) {
        OpenWeatherDTO weather = null;
        if ((BASE_URI!=null)&&(apiOpenWeatherToken!=null)){
            webTarget = client.target(BASE_URI);    // establece la URI inicial (host:puerto/nombreApp/rest)
            webTarget = webTarget.path("weather");  // añade descriptor del servicio web a utilizar
            webTarget = webTarget.queryParam("q", _city);   // añade parámetro a la URI: q, units, lang y appid
            webTarget = webTarget.queryParam("units", "metric");
            webTarget = webTarget.queryParam("lang", "ES");
            webTarget = webTarget.queryParam("appid", apiOpenWeatherToken);
            
System.out.println("===> "+webTarget.getUri().toString()); // comprueba la URI final
            try{
                // una vez construida la URI, lanza la petición
                weather = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(OpenWeatherDTO.class);
            }catch(Exception e){
                weather = null;
            }
        }
        return weather;
    }
      
      
      
      
       public OpenWeatherDTO getWeatherNowByCoord(Double _lat, Double _lon) {
        OpenWeatherDTO weather = null;
        if ((BASE_URI!=null)&&(apiOpenWeatherToken!=null)){
            webTarget = client.target(BASE_URI);
            webTarget = webTarget.path("weather");
            webTarget = webTarget.queryParam("lat", _lat);
            webTarget = webTarget.queryParam("lon", _lon);
            webTarget = webTarget.queryParam("appid", apiOpenWeatherToken);
            webTarget = webTarget.queryParam("units", "metric");
            webTarget = webTarget.queryParam("lang", "ES");

            
System.out.println("===> "+webTarget.getUri().toString());

            try{
                weather = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(OpenWeatherDTO.class);
            }catch(Exception e){
                weather = null;
            }          
        }
        return weather;
    }
       
  
       
    public String getForecastByCity(String _city)  {
        String json = "{}";
        if ((BASE_URI!=null)&&(apiOpenWeatherToken!=null)){
            webTarget = client.target(BASE_URI);
            webTarget = webTarget.path("forecast");
            webTarget = webTarget.queryParam("q", _city);
            webTarget = webTarget.queryParam("appid", apiOpenWeatherToken);
            webTarget = webTarget.queryParam("units", "metric");
            webTarget = webTarget.queryParam("lang", "ES");
            
System.out.println("===> "+webTarget.getUri().toString());

            try{
                json = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
            }catch (Exception ex){
                json = null;
            }
        }
        return json;
    }

    
    public void close() {
        client.close();
    }
    
}
