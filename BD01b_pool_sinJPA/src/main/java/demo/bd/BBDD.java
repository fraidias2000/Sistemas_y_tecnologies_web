/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Usuario
 */
public class BBDD {

    private static BBDD moiMeme = null;
    private InitialContext ic;
    private DataSource ds;
    
    
    private BBDD(){
        try {
            ic = new InitialContext();
            ds = (DataSource)ic.lookup("jdbc/demoPool");
        } catch (NamingException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public static BBDD getInstance(){
        if (moiMeme==null){
            moiMeme = new BBDD();
        }
        return moiMeme;
    }
    
    
    
    private Connection getConnectionFromPool(){
        Connection conn=null;
        try {
            conn = ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    
    
    public List<Cliente> getAllClientes(){
        List<Cliente> cc = new ArrayList();
        Connection conn = getConnectionFromPool();  // ==>>> obtenemos la conexión del pool
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Cliente";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Cliente c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setAp1(rs.getString("ap1"));
                    c.setSaldo(rs.getDouble("saldo"));
                    cc.add(c);
                }
                rs.close();
                conn.close(); // ==>>> devolvemos la conexión al pool

            } catch (SQLException ex) {
                Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cc;
    }
  
    
    public Cliente getCliente (Integer _id){
        Cliente c = null;
        Connection conn = getConnectionFromPool();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Cliente WHERE id="+_id;
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setAp1(rs.getString("ap1"));
                    c.setSaldo(rs.getDouble("saldo"));
                 }
                rs.close();
                conn.close(); //==>>> devolvemos la conexión al pool
            } catch (SQLException ex) {
                Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return c;
    }
    
    
    public void insertCliente( Cliente _c){
        
        // ...
        
    }
    
    public void deleteCliente( Cliente _c){
        
        // ...
        
    }
    
  
}
