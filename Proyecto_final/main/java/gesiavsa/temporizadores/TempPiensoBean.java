/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/
package gesiavsa.temporizadores;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import gesiavsa.muelles.wsocket.WebSocketManagerPienso;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fserna
 */
@Stateless
public class TempPiensoBean {

    private final Double FIN_DESCARGA     = 0.0;    
    private Double nivelTempPienso      = 251.0;  
    private boolean descargaPiensoOn    = false;
    private Double tiempoResta         = 1.0;
    
    @EJB  WebSocketManagerPienso websocketPienso;
    
    @PostConstruct
    public void init(){
        //websocket = WebSocketManagerPienso.getInstance();
    }
    
    @PreDestroy
    private void sayBye(){
        //if (websocketPienso!=null){
            websocketPienso.destroy();  // propaga el evento de "destroy" al elemento
                                 // que gestiona las sesiones websocketPienso, 
                                 // para que puedan ser cerradas convenientemente.
        //}
    }
    
    @Schedule (hour="*", minute="*", second="*/1")
    public void updateNivel(){
        if (descargaPiensoOn){
            this.nivelTempPienso = this.nivelTempPienso - tiempoResta;
            if (this.nivelTempPienso <= FIN_DESCARGA){
                setNivelTempPienso(FIN_DESCARGA);
                descargaPiensoOn = false;
            }
            websocketPienso.broadcastValorNumerico("tempPienso", 
                                        this.nivelTempPienso.toString());
        }     
    }    
    
    public double getNivelTempPienso(){
        return this.nivelTempPienso;
    }
    
    public void setNivelTempPienso(double _tiempoRestante){
        this.nivelTempPienso = _tiempoRestante;
    } 
    
    public boolean isDescargaPiensoOn() {
        return descargaPiensoOn;
    }

    public void setDescargaPiensoOn(boolean _descargaPiensoOn) {
        this.descargaPiensoOn = _descargaPiensoOn;
        if (this.descargaPiensoOn){
            websocketPienso.broadcastMsg("descargarPienso");
        }else{
            websocketPienso.broadcastMsg("pararDescargaPienso");
        }
    }
}
