package stw.weatherr.dto.usandoJavax.json;

import com.google.gson.Gson;
import java.io.Serializable;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fsernafortea
 */
@XmlRootElement
public class ForecastWeatherDTO implements Serializable {
    private int  cod;
    private long    msRX;
    private int     cityCode;
    private String  city;
    private String  country;
    private double  lat;
    private double  lon;
    private int     numItems;  // number of ForecastItems
    private ForecastItem[] items;


    
    public ForecastWeatherDTO(){
    }


    public ForecastWeatherDTO(String _jsonString){
        if (_jsonString !=null){
            try {
                JsonReader jsonReader = Json.createReader(new StringReader(_jsonString));
                JsonObject object = jsonReader.readObject();
                this.setMsRX        (System.currentTimeMillis());
                this.setCod         (Integer.valueOf(object.getJsonString("cod").getString()));
                if (this.cod==200){
                    this.setNumItems    (object.getJsonNumber("cnt").intValue());
                    this.setCityCode    (object.getJsonObject("city").getJsonNumber("id").intValue());
                    this.setCity        (object.getJsonObject("city").getJsonString("name").getString());
                    this.setCountry     (object.getJsonObject("city").getString("country"));
                    this.setLat         (object.getJsonObject("city").getJsonObject("coord").getJsonNumber("lat").doubleValue());
                    this.setLon         (object.getJsonObject("city").getJsonObject("coord").getJsonNumber("lon").doubleValue());

                    JsonArray list = object.getJsonArray("list");
                    if (list!=null){
                        items = new ForecastItem[list.size()];
                        for (int i=0; i<list.size(); i++){
                            JsonObject jo = list.getJsonObject(i);
                            if (jo!=null){
                                items[i] = new ForecastItem(jo);
                            }
                        }
                    }
                }// cod 200
                jsonReader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
   }
        
    /**
     * @return the msRX
     */
    public long getMsRX() {
        return msRX;
    }

    /**
     * @param msRX the msRX to set
     */
    public void setMsRX(long msRX) {
        this.msRX = msRX;
    }
    
    
    /**
     * @return the cityCode
     */
    public int getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode the cityCode to set
     */
    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    
    public String toJson(){
        String json ="";
        Gson gson = new Gson();
        json = gson.toJson(this);
        return json;
    }

    /**
     * @return the numItems
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * @param numItems the numItems to set
     */
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return the items
     */
    public ForecastItem[] getForecastItems() {
        return items;
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int _cod) {
        this.cod = _cod;
    }


    
    
}
