/**
 * @author Alvaro Fraidias NIP 780336
 
 * Referencias utilizadas:
 * demos de @fserna
 */

package gesiavsa.bd;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Usuario
 */
@Entity
public class Transportista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
    private String opcion;
    private String numeroMatricula;
    private Calendar horaRegistro;

    public Calendar getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(Calendar horaRegistro) {
        this.horaRegistro = horaRegistro;
    }
    
    
    
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }


      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transportista)) {
            return false;
        }
        Transportista other = (Transportista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.bd.Cliente[ id=" + id + " ]";
    }
    
    public String parsearFecha(){
     int anyo = horaRegistro.get(Calendar.YEAR);
        int mes = horaRegistro.get(Calendar.MONTH) + 1;
        int dia = horaRegistro.get(Calendar.DAY_OF_MONTH);
        int hora = horaRegistro.get(Calendar.HOUR_OF_DAY);
        int minuto = horaRegistro.get(Calendar.MINUTE);
        int segundo = horaRegistro.get(Calendar.SECOND);
        return dia + "/" + mes + "/" + anyo + " " + hora + ":" + minuto + ":" + segundo;
    
    }
    
}
