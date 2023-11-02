package fs.chat.websocket;


import fs.chat.ejb.ChatStorage;
import fs.chat.ejb.Usuarios;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author fserna
 */

@Stateless
@ServerEndpoint("/chat")
public class WebSocketManager {

    @EJB Usuarios usuariosRegistrados;
    @EJB ChatStorage almacen;
    private Session session; // última session establecida. No sabemos a quién pertenece.
                             // en realidad solo la queremos para poder usar el 
                             // método "getOpenSessions()"

    
    @OnOpen
    public void onOpen(Session _session){

        this.session = _session;
        
        this.publishUsuariosRegistrados(usuariosRegistrados.getNombres());
        try {
           for (String json: almacen.getHistorico()){
                session.getBasicRemote().sendText(json);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.IllegalStateException ise){
            
        }
    }
     
    @OnMessage
    public String onMessage(Session _session, String _message) {
        
        System.out.println("== RX ==> "+_message);
        almacen.storeMessage(_message);
        
        // broadcast del mensaje recibido
        for (Session s: _session.getOpenSessions()){
            try {
                s.getBasicRemote().sendText(_message);
            } catch (IOException ex) {
              
            }
        }
        return null;
    }
    
    @OnClose
    public void onClose(Session _session){

    }
    
    
    public void sendToClients (String _json){
        try {
            for (Session s : session.getOpenSessions()) {
                s.getBasicRemote().sendText(_json);
            }
        } catch (IOException ex) {
        }
    }    
    
    
    public void publishUsuariosRegistrados(List<String> _uu){
        String json = "{\"cmd\": \"usuarios\", \"registrados\": [";

        int n= 0;
        for (String u: _uu){
            if (n>0){
                json += ", ";
            }
            n++;
            json += "\""+u+"\"";
        }
        json += "]}";
System.out.println(json);

        if (session!=null){
            try {
                for (Session s : session.getOpenSessions()) {
                    s.getBasicRemote().sendText(json);
                }
            } catch (IOException ex) {
            }
        }
    }
}
