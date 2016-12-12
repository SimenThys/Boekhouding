/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import beans.SessionBeanLocalInterface;
import beans.SessionBeanRemoteInterface;
import java.io.IOException;
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
        HttpSession sessie = request.getSession();
        String ganaar = "JSP-Werknemer/overzicht.jsp";
        if (sessie.isNew())
        {
            System.out.println("Nieuwe sessie!");
        }
        else
        {
            System.out.println("Hello again!");
        }
        int wnr = Integer.parseInt(request.getUserPrincipal().getName());
        int type = localbean.OpvragenType(wnr);
        sessie.setAttribute("type", type);
        switch((int)sessie.getAttribute("type"))
        {
            case 0: ganaar = "JSP-Werknemer/overzicht.jsp";
                    break;
            case 1: ganaar = "JSP-Boekhouder/keuze-Boekhouder.jsp";
                    break;
            case 2: ganaar = "JSP-Manager/keuze-Manager.jsp";
                    break;
        }
        gotoPage(ganaar,request,response);
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
