/*
  @author Rafael Rodriguez NIP 564786
  
  Referencias utilizadas:
  demos de @fserna
*/
package gesiavsa.temporizadores;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import gesiavsa.muelles.wsocket.WebSocketManagerGallinas;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TempGallinasBean {

    private final Double FIN_DESCARGA     = 0.0;    
    private Double nivelTempGallinas      = 250.0;  
    private boolean descargaGallinasOn    = false;
    private Double tiempoResta         = 25.0;
    
   @EJB WebSocketManagerGallinas websocketGallinas;
    
    @PostConstruct
    public void init(){
        //websocket = WebSocketManager.getInstance();
    }
    
    @PreDestroy
    private void sayBye(){
        //if (websocketGallinas!=null){
            websocketGallinas.destroy();  // propaga el evento de "destroy" al elemento
                                 // que gestiona las sesiones websocketGallinas, 
                                 // para que puedan ser cerradas convenientemente.
        //}
    }
    
    @Schedule (hour="*", minute="*", second="*/1")
    public void updateNivel(){
        if (descargaGallinasOn){
            this.nivelTempGallinas = this.nivelTempGallinas - tiempoResta;
            if (this.nivelTempGallinas <= FIN_DESCARGA){
                setNivelTempGallinas(FIN_DESCARGA);
                descargaGallinasOn = false;
            }
            websocketGallinas.broadcastValorNumerico("tempGallinas", this.nivelTempGallinas.toString());

            
        }     
    }    
    
    public double getNivelTempGallinas(){
        return this.nivelTempGallinas;
    }
    
    public void setNivelTempGallinas(double _tiempoRestante){
        //this.nivelTempGallinas = 260.0;
        this.nivelTempGallinas = _tiempoRestante;
    } 
    
    public boolean isDescargaGallinasOn() {
        return descargaGallinasOn;
    }

    public void setDescargaGallinasOn(boolean _descargaGallinasOn) {
        this.descargaGallinasOn = _descargaGallinasOn;
        if (this.descargaGallinasOn){
            websocketGallinas.broadcastMsg("descargarGallinas");
        }else{
            websocketGallinas.broadcastMsg("pararDescargaGallinas");
        }
    }
}
