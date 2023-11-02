/**
 * @author Rafael Rodriguez NIP 564786
 
 * Referencias utilizadas:
 * demos de @fserna
 */

package gesiavsa.muelles.wsocket;

import gesiavsa.temporizadores.TempGallinasBean;

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
@ServerEndpoint("/wsmTempGallinas") 
public class WebSocketManagerGallinas {

    @EJB TempGallinasBean tempDescargaGallinas;
    private Set<Session> sessions = new HashSet<Session>(); 
    private final double INICIO_TEMP = 260.0;
    private final double FIN_TEMP = 0.0;

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

        if (tempDescargaGallinas.isDescargaGallinasOn()){
            broadcastMsg("descargarGallinas");
        }else{
            broadcastMsg("pararDescargaGallinas");
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
            case "iniciarDescargarGallinas":
                tempDescargaGallinas.setNivelTempGallinas(INICIO_TEMP);
                tempDescargaGallinas.setDescargaGallinasOn(true);
                break;

            case "pararDescargaGallinas":
                tempDescargaGallinas.setNivelTempGallinas(FIN_TEMP);
                tempDescargaGallinas.setDescargaGallinasOn(false);
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
        System.out.println("xxx WebSockerManagerGallinas says Bye! ---------------");
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
     * @param _temp
     * @param _v 
     */    
    public void broadcastValorNumerico(String _temp, String _v){
        try {
            for (Session session : sessions) {
                String text = (_temp + " " + _v);
                System.out.println(text);
                session.getBasicRemote().sendText(text);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManagerGallinas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WebSocketManagerGallinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
