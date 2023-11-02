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
public class Clouds implements Serializable{
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }    
    
}
