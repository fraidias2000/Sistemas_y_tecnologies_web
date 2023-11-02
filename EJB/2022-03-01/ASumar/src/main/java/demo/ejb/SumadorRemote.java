package demo.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.ejb.Remote;

/**
 *
 * @author usuario
 */
@Remote
public interface SumadorRemote {
    public Integer efectuarSuma(Integer _s1, Integer _s2) ;
}
