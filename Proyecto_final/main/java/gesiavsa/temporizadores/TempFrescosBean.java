/* 
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/

package gesiavsa.temporizadores;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import gesiavsa.muelles.wsocket.WebSocketManagerFrescos;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fserna
 */
@Stateless
public class TempFrescosBean {

    private final Double NIVEL_MAXIMO       = 0.0;      
    private Double nivelTempFrescos         = 250.0;      
    private boolean cargaFrescosOn          = false;
    private Double tiempoResta         = 2.0;
    
    @EJB  WebSocketManagerFrescos websocketFrescos;
    
    @PostConstruct
    public void init(){
        //websocket = WebSocketManagerFrescos.getInstance();
    }
    
    @PreDestroy
    private void sayBye(){
        //if (websocketFrescos!=null){
            websocketFrescos.destroy();  // propaga el evento de "destroy" al elemento
                                 // que gestiona las sesiones websocketFrescos, 
                                 // para que puedan ser cerradas convenientemente.
        //}
    }
    
    @Schedule (hour="*", minute="*", second="*/1")
    public void updateNivel(){
        if (cargaFrescosOn){
            this.nivelTempFrescos = this.nivelTempFrescos - tiempoResta;
            if (this.nivelTempFrescos <= NIVEL_MAXIMO){
                this.nivelTempFrescos = NIVEL_MAXIMO;
            }
            websocketFrescos.broadcastValorNumerico("tempFrescos", this.nivelTempFrescos.toString());
        }
    }    
    
    public double getNivelTempFrescos(){
        return this.nivelTempFrescos;
    }
    
    public void setNivelTempFrescos(Double _tiempoRestante){
        //this.nivelTempFrescos = 260.0;
        this.nivelTempFrescos = _tiempoRestante;
    } 
    
    public boolean isCargaFrescosOn() {
        return cargaFrescosOn;
    }

    public void setCargaFrescosOn(boolean _cargaFrescosOn) {
        this.cargaFrescosOn = _cargaFrescosOn;
        if (this.cargaFrescosOn){
            websocketFrescos.broadcastMsg("cargarFrescos");
        }else{
            websocketFrescos.broadcastMsg("pararCargaFrescos");
        }
    }
}
