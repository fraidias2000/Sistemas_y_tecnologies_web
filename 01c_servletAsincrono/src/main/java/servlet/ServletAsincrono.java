/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import demo.Aburridor;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fserna
 */
@WebServlet(name = "servletAsincrono", 
            urlPatterns = {"/servletAsincrono"}, 
            asyncSupported=true)
public class ServletAsincrono extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        session.setAttribute("msSync", null);
        session.setAttribute("t0Sync", null);

        final long n = Long.valueOf(request.getParameter("numVeces")).longValue();
        long t0 = System.currentTimeMillis();
        
        final AsyncContext acontext = request.startAsync();
        acontext.start (new Runnable(){
            public void run(){
                    Aburridor ab = new Aburridor();
                    ab.aburrir(n);
                    acontext.complete();
             }// run
        }); // runnable
        
        
        
        long t1 = System.currentTimeMillis();
        session.setAttribute("msAsync", (t1-t0));
        session.setAttribute("t0Async", t0);

        //request.getRequestDispatcher("index.jsp").forward(request, response);
        response.sendRedirect(response.encodeURL("index.jsp"));

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
