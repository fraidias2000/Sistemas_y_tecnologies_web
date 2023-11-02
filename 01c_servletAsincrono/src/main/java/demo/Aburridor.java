/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

/**
 *
 * @author fserna
 */
public class Aburridor {
    public Aburridor(){
        
    }
    
    public double aburrir(long _numVeces){
        double x = 3.1;
        
        for (long i=0L; i< _numVeces; i++){
            x = x * i + (i/3)*Math.sqrt(x);
        }
        return x;
    }
}
