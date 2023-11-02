/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/

package gesiavsa.temporizadores;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import gesiavsa.muelles.wsocket.WebSocketManagerCocidos;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TempCocidosBean {

    private final Double FIN_CARGA     = 0.0;    
    private Double nivelTempCocidos       = 250.0;        
    private boolean cargaCocidosOn        = false;
    private Double tiempoResta         = 5.0;
    
    @EJB  WebSocketManagerCocidos websocketCocidos;
    
    @PostConstruct
    public void init(){
        //websocket = WebSocketManagerCocidos.getInstance();
    }
    
    @PreDestroy
    private void sayBye(){
        //if (websocketCocidos!=null){
            websocketCocidos.destroy();  // propaga el evento de "destroy" al elemento
                                 // que gestiona las sesiones websocketCocidos, 
                                 // para que puedan ser cerradas convenientemente.
        //}
    }
    
    @Schedule (hour="*", minute="*", second="*/1")
    public void updateNivel(){
        if (cargaCocidosOn){
            this.nivelTempCocidos = this.nivelTempCocidos - tiempoResta;
            if (this.nivelTempCocidos <= FIN_CARGA){
                this.nivelTempCocidos = FIN_CARGA;
                cargaCocidosOn = false;                
            }
            websocketCocidos.broadcastValorNumerico("tempCocidos", this.nivelTempCocidos.toString());
        }
    }    
    
    public double getNivelTempCocidos(){
        return this.nivelTempCocidos;
    }
    
    public void setNivelTempCocidos(double _tiempoRestante){
//        this.nivelTempCocidos = 260.0;
        this.nivelTempCocidos = _tiempoRestante; 
    } 
    
    public boolean isCargaCocidosOn() {
        return cargaCocidosOn;
    }

    public void setCargaCocidosOn(boolean _cargaCocidosOn) {
        this.cargaCocidosOn = _cargaCocidosOn;
        if (this.cargaCocidosOn){
            websocketCocidos.broadcastMsg("cargarCocidos");
        }else{
            websocketCocidos.broadcastMsg("pararCargaCocidos");
        }
    }
    
}
