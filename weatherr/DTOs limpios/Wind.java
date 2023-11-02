/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stw.weatherr.dto;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author fsern
 */
public class Wind implements Serializable{
    private Double speed;
    private Integer deg;
    private Double gust;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed*3.6;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust*3.6;
    }
    
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }    
    
    
}
