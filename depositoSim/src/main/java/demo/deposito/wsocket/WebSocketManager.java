/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.deposito.wsocket;

import demo.deposito.DepositoBean;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
/** 
 * @ServerEndpoint gives the relative name for the websocket end point
 * This will be accessed via ws://<hostName>:8080/<appName>/deposito
 * 
 * Está implementado como un EJB stateless singleton, pero podría ser 
 * también un POJO singleton. En ese caso la variable "deposito" habría
 * que obtenerla mediante JNDI lookup (InitialContext, .lookup("JNDIname"), etc);
 */

@Singleton
@ServerEndpoint("/deposito") 
public class WebSocketManager {

    @EJB DepositoBean deposito;
    private Set<Session> sessions = new HashSet<Session>(); 


    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session _session){
        System.out.println(">>> Session " +_session.getId()+" created");
        sessions.add(_session);

        if (deposito.isGrifoIn()){
            broadcastMsg("grifoInABIERTO");
        }else{
            broadcastMsg("grifoInCERRADO");
        }
        if (deposito.isGrifoOut()){
            broadcastMsg("grifoOutABIERTO");
        }else{
            broadcastMsg("grifoOutCERRADO");
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it.For now the message is read as a String.
     * @param _message
     */
    @OnMessage
    public void onMessage(String _message, Session _session){
       System.out.println("======== MSG RX: "+_message);
       switch (_message){
           case "openGrifoIn":
               deposito.setGrifoIn(true);
               break;
               
           case "closeGrifoIn":
               deposito.setGrifoIn(false);
               break;
               
           case "openGrifoOut":
               deposito.setGrifoOut(true);
               break;
               
           case "closeGrifoOut":
               deposito.setGrifoOut(false);
               break;
       }
       
       
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session _session){
        System.out.println("--- Session " +_session.getId()+" has ended");
        sessions.remove(_session);
    }
    
    

    public void destroy(){
        System.out.println("xxx WebSockerManager says Bye! ---------------");
        for (Session s: sessions){
            try {
                s.close();
            } catch (IOException ex) {
            }
        }
        sessions.clear();
    }


    @OnError
    public void onError(Session _session, Throwable t) {
        System.out.println("--- ERROR in session " +_session.getId());
    }


    /**
     * Envía el valor numérico "_v" a todas las sesiones existentes (broadcast)
     * @param _v 
     */
    public void broadcastValorNumerico(double _v){
        try {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(String.valueOf(_v));
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Envía el texto "_msg" a todas las sesiones existentes (broadcast)
     * @param _msg 
     */
    public void broadcastMsg(String _msg){
        try {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(_msg);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
