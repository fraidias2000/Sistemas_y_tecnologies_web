/**
 * @author Alvaro Fraidias NIP 780336
 
 * Referencias utilizadas:
 * demos de @fserna
 */

package gesiavsa.bd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TransportistaDAO extends AbstractFacade<Transportista> {

    @PersistenceContext(unitName = "gesiavsa1_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransportistaDAO() {
        super(Transportista.class);
    }
    
     public boolean existeTransportista (String _nombreUsuario, String _contrasenia){
        boolean existe = false;
        Query query = em.createQuery("SELECT t FROM Transportista t WHERE t.nombreUsuario=:nombreUsuario AND"
                                                                       + "t.contrasenia=:contrasenia");
        query.setParameter("nombreUsuario", _nombreUsuario);
        query.setParameter("contrasenia", _contrasenia);
        if (query.getResultList().size() > 0){
            existe = true;
        }
        return existe;
    }
    
}
