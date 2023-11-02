/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package fs.chat.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Usuario
 */
@Stateless
public class ChatStorage {

   private List<String> jsonHistorico = new ArrayList();

    public void storeMessage(String _json) {
        jsonHistorico.add(_json);
    }
    
    
    public List<String> getHistorico(){
        return this.jsonHistorico;
    } 
   
   
}
