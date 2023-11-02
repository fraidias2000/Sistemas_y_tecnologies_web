/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author usuario
 */
@Stateless
public class Sumador implements SumadorRemote {

    public Integer efectuarSuma(Integer _s1, Integer _s2) {
        Integer suma = _s1 + _s2;
        System.out.println("ModuloAritmetico.Sumador.efectuarSuma: Me han pedido que sume: " +  _s1 + " + " + _s2 + " = " + suma);
        return suma;
    }    
}
