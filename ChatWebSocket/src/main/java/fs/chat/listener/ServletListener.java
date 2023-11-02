/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package fs.chat.listener;

import fs.chat.ejb.Usuarios;
import fs.chat.websocket.WebSocketManager;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author fsern
 */
public class ServletListener implements ServletContextListener, HttpSessionListener {

    @EJB WebSocketManager wsm;
    @EJB Usuarios usuarios;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String login = (String)se.getSession().getAttribute("login");
        if (login!=null){
            System.out.println("Fin de la sesión de: "+login);
            usuarios.eliminarNombre(login);
            wsm.publishUsuariosRegistrados(usuarios.getNombres());
        }
    }
}
