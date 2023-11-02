/**
 * @author Alvaro Fraidias NIP 780336
 * @author Rafael Rodriguez NIP 564786
 
 * Referencias utilizadas:
 * demos de @fserna
 */

package gesiavsa.servlets;

import gesiavsa.bd.Transportista;
import gesiavsa.bd.TransportistaDAO;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SalidaTransportista", urlPatterns = {"/salidaTransportista"})
public class SalidaTransportista extends HttpServlet {

    @EJB TransportistaDAO transportistaDB;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // <<=== NECESARIO para que funcionen las tildes, Ñs, etc... 
        String paginaRetorno      = request.getParameter("paginaRetorno");
        String numeroMatricula      = request.getParameter("matriculaEntrada");
        String opcion   = request.getParameter("opcion");

        if((numeroMatricula != null && opcion!= null)||(!numeroMatricula.equals(""))){
            Calendar fecha = Calendar.getInstance();
            Transportista t = new Transportista();
            t.setNumeroMatricula(numeroMatricula);
            t.setOpcion(opcion);
            t.setHoraRegistro(fecha);       
            transportistaDB.create(t);
            
        }else{
            System.out.println("Transportista no creado");
        }   

        response.sendRedirect(response.encodeRedirectURL(paginaRetorno));
        
        
    }
    


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
