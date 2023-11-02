/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stw.weatherr.dto;

import com.google.gson.Gson;

/**
 *
 * @author fsern
 */
public class Coord {
    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }    
}
