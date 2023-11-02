
package stw.weatherr.dto.usandoJavax.json;

import com.google.gson.Gson;
import java.io.Serializable;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fsernafortea
 */

@XmlRootElement
public class ForecastItem implements Serializable{
    private long dt;   // time of data calculation
    private String dt_txt;   // time of data calculation
    private String description; // weather description
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double pressure_sea_level;
    private double humidity;
    private double wind_speed;
    private int wind_deg;
    private int clouds;  // % cloudiness
    private double rain_3h; 
    private double snow_3h; 


   public ForecastItem (){
   }

   
    public ForecastItem (JsonObject _jo){
        if (_jo!=null){
            this.setDt          (_jo.getJsonNumber("dt").intValue());
            this.setDt_txt      (_jo.getJsonString("dt_txt").getString());
            this.setTemp        (_jo.getJsonObject("main").getJsonNumber("temp").doubleValue());
            this.setTemp_max    (_jo.getJsonObject("main").getJsonNumber("temp_max").doubleValue());
            this.setTemp_min    (_jo.getJsonObject("main").getJsonNumber("temp_min").doubleValue());
            this.setPressure    (_jo.getJsonObject("main").getJsonNumber("pressure").doubleValue());
            this.setPressure_sea_level(_jo.getJsonObject("main").getJsonNumber("sea_level").doubleValue());
            this.setHumidity    (_jo.getJsonObject("main").getJsonNumber("humidity").doubleValue());
            this.setDescription (_jo.getJsonArray("weather").getJsonObject(0).getString("description"));
            this.setWind_speed  (_jo.getJsonObject("wind").getJsonNumber("speed").doubleValue());
            this.setWind_deg    (_jo.getJsonObject("wind").getJsonNumber("deg").intValue());
            this.setClouds      (_jo.getJsonObject("clouds").getJsonNumber("all").intValue());
            if (_jo.getJsonObject("rain")!=null){
                if (_jo.getJsonObject("rain").getJsonNumber("3h")!=null){
                    this.setRain_3h      (_jo.getJsonObject("rain").getJsonNumber("3h").doubleValue());
                }else{
                    this.setRain_3h(0.0);    
                }
            }else{
                this.setRain_3h(0.0);
            }
            if (_jo.getJsonObject("snow")!=null){
                if (_jo.getJsonObject("snow").getJsonNumber("3h")!=null){
                    this.setSnow_3h(_jo.getJsonObject("snow").getJsonNumber("3h").doubleValue());
                }else{
                    this.setSnow_3h(0.0);
                }
            }else{
                this.setSnow_3h(0.0);
            }
        }
   }
    
    /**
     * @return the msRX
     */
    public long getDt() {
        return dt;
    }

    /**
     * 
     */
    public void setDt(long _dt) {
        this.dt = _dt*1000;
    }


    /**
     * @return the dt_txt
     */
    public String getDt_txt() {
        return dt_txt;
    }

    /**
     * @param _dt_txt the dt_txt to set
     */
    public void setDt_txt(String _dt_txt) {
        this.dt_txt = _dt_txt;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @param _temp the temp to set
     */
    public void setTemp(double _temp) {
        this.temp = _temp-273;
    }

    /**
     * @return the pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return the humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * @return the temp_min
     */
    public double getTemp_min() {
        return temp_min;
    }

    /**
     * @param _temp_min the temp_min to set
     */
    public void setTemp_min(double _temp_min) {
        this.temp_min = _temp_min-273;
    }

    /**
     * @return the temp_max
     */
    public double getTemp_max() {
        return temp_max;
    }

    /**
     * @param _temp_max the temp_max to set
     */
    public void setTemp_max(double _temp_max) {
        this.temp_max = _temp_max-273;
    }

    
    /**
     * @return the wind_speed
     */
    public double getWind_speed() {
        return wind_speed;
    }
    
      /**
     * @return the wind_speed in Km/h
     */
    public double getWind_speed_Kmh() {
        return wind_speed*3.6;
    }

    /**
     * @param wind_speed the wind_speed to set
     */
    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    /**
     * @return the wind_deg
     */
    public int getWind_deg() {
        return wind_deg;
    }

    /**
     * @param wind_deg the wind_deg to set
     */
    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

   
    
    
    public String toJson(){
        String json ="";
        Gson gson = new Gson();
        json = gson.toJson(this);
        return json;
    }


    /**
     * @return the pressure_sea_level
     */
    public double getPressure_sea_level() {
        return pressure_sea_level;
    }

    /**
     * @param pressure_sea_level the pressure_sea_level to set
     */
    public void setPressure_sea_level(double pressure_sea_level) {
        this.pressure_sea_level = pressure_sea_level;
    }
    
     /**
     * @return the clouds
     */
    public int getClouds() {
        return clouds;
    }

    /**
     * @param clouds the clouds to set
     */
    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    /**
     * @return the rain_3h
     */
    public double getRain_3h() {
        return rain_3h;
    }

    /**
     * @param rain_3h the rain_3h to set
     */
    public void setRain_3h(double rain_3h) {
        this.rain_3h = rain_3h;
    }

    /**
     * @return the snow_3h
     */
    public double getSnow_3h() {
        return snow_3h;
    }

    /**
     * @param snow_3h the snow_3h to set
     */
    public void setSnow_3h(double snow_3h) {
        this.snow_3h = snow_3h;
    }
    
}
