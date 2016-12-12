/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import beans.SessionBeanLocalInterface;
import beans.SessionBeanRemoteInterface;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
public class ResController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ServletConfig servConf; 
    
    @EJB 
    private SessionBeanLocalInterface localbean;
    
    @EJB
    private SessionBeanRemoteInterface remotebean;
    
    @Override
    public void init() throws ServletException {
        this.servConf = getServletConfig();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String stage = request.getParameter("stage");
        HttpSession sessie = request.getSession();
        
        if (stage == null){
            int wnr = Integer.parseInt(request.getUserPrincipal().getName());
            int type = localbean.OpvragenType(wnr);
            sessie.setAttribute("type", type);
            
            switch(type)
            {
                case 0: gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
                        break;
                case 1: gotoPage("JSP-Boekhouder/keuze-Boekhouder.jsp",request,response);
                        break;
                case 2: gotoPage("JSP-Manager/keuze-Manager.jsp",request,response);
                        break;
            }
            
            System.out.println("Nieuwe sessie!");
        }
        if( stage.equals("boekhouder_keuze")){
            String keuze = request.getParameter("keuze");
            if(keuze.equals("overzicht"))
                gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
            if(keuze.equals("krediet"))
            {
                List kredieten = localbean.OpvragenBoekhouder();
                sessie.setAttribute("Kredieten", kredieten);
                gotoPage("JSP-Boekhouder/kredieten.jsp",request,response);
            }
        }
        else
        {
            System.out.println("Hello again!");
            gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
        }
    }
    
    private void gotoPage(String jspnaam, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        RequestDispatcher view = request.getRequestDispatcher(jspnaam); 
        view.forward(request, response);
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
