/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Usuario
 */
@WebListener
public class OyenteSesiones implements HttpSessionListener {
    
    
    @Override
    public void sessionCreated(HttpSessionEvent _se) {
        System.out.println("=== Sesión creada ====== "+_se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent _se) {
        System.out.println("*** Sesión destroyed *** "+_se.getSession().getId());
        _se.getSession().invalidate();
    }

   
}
