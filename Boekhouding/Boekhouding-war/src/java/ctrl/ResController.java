/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import beans.SessionBeanLocalInterface;
import beans.SessionBeanRemoteInterface;
import databaseBeans.Kredieten;
import databaseBeans.Onkosten;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import java.util.List;
import java.util.Map;
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
        
        String ganaar = request.getParameter("ganaar");
        HttpSession sessie = request.getSession();
        
        if (ganaar == null){
            int wnr = Integer.parseInt(request.getUserPrincipal().getName());
            int type = localbean.OpvragenType(wnr);
            sessie.setAttribute("type", type);
            sessie.setAttribute("wnr", wnr);
            switch(type)
            {
                case 0: ganaar="werknemer_overzicht";
                        break;
                case 1: gotoPage("JSP-Boekhouder/keuze-Boekhouder.jsp",request,response);
                        break;
                case 2: gotoPage("JSP-Manager/keuze-Manager.jsp",request,response);
                        break;
            }
        }
        
        if(ganaar.equals("boekhouder_krediet"))
        {
            List kredieten = localbean.OpvragenBoekhouder();
            sessie.setAttribute("kredieten", kredieten);
            gotoPage("JSP-Boekhouder/kredieten.jsp",request,response);
        }
        
        if(ganaar.equals("werknemer_overzicht"))
        {
            List onkosten = localbean.OpvragenWerknemer((int)sessie.getAttribute("wnr"));
            sessie.setAttribute("onkosten", onkosten);
            gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
        }
        
        if(ganaar.equals("overzicht_status"))
        {
            Onkosten onkost = (Onkosten)localbean.OpvragenOnkost(Integer.parseInt(request.getParameter("vraagonkostop")));
            request.setAttribute("isnieuw","oud");
            request.setAttribute("gevraagdeonkost",onkost);
            gotoPage("JSP-Werknemer/status.jsp",request,response);
        }
        
        if(ganaar.equals("status_overzicht"))
        {
            gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
        }
        
        if(ganaar.equals("status_keuze"))
        {
            String keuze = request.getParameter("keuze");
            if(keuze.equals("Tijdelijk opslaan"))
            {   
                if(Integer.parseInt(request.getParameter("bedrag"))!=0)
                {
                    if(request.getParameter("isnieuw").equals("nieuw"))
                        localbean.OnkostToevoegen((int)sessie.getAttribute("wnr"),Integer.parseInt(request.getParameter("bedrag")),request.getParameter("omschr"));
                    localbean.editOnkost(Integer.parseInt(request.getParameter("bedrag")),Integer.parseInt(request.getParameter("onr")),request.getParameter("omschr")); 
                }
                List onkosten = localbean.OpvragenWerknemer((int)sessie.getAttribute("wnr"));
                sessie.setAttribute("onkosten", onkosten);
                gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
            }
            if(keuze.equals("Doorsturen"))
            {
                List gewoonk = new ArrayList<Kredieten>();
                List ondernulk = new ArrayList<Kredieten>();
                Map<Kredieten, Integer> kredietmap = localbean.OpvragenOnkostAanvragen((int)sessie.getAttribute("wnr"),Integer.parseInt(request.getParameter("bedrag")));
                System.out.print(kredietmap);
                for (Map.Entry<Kredieten, Integer> entry : kredietmap.entrySet())
                {
                    System.out.println(entry.getKey().getKnr());
                    if(entry.getValue()==0)
                    {
                        gewoonk.add(entry.getKey());
                    }
                    if(entry.getValue()==1)
                    {
                        ondernulk.add(entry.getKey());
                    }
                }           
                sessie.setAttribute("gewoonk", gewoonk);
                sessie.setAttribute("ondernulk", ondernulk);
                request.setAttribute("bedrag",request.getParameter("bedrag"));
                request.setAttribute("omschr",request.getParameter("omschr"));
                request.setAttribute("onr",request.getParameter("onr"));
                gotoPage("JSP-Werknemer/kieskrediet.jsp",request,response);
            }
            if(keuze.equals("Vorige"))
            {
                gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
            }
        }
            
        if(ganaar.equals("overzicht_nieuw"))
        {
            Onkosten onkost = localbean.tempOnkost((int)sessie.getAttribute("wnr"));
            request.setAttribute("isnieuw","nieuw");
            request.setAttribute("gevraagdeonkost",onkost);
            gotoPage("JSP-Werknemer/status.jsp",request,response);
        }
        
        if(ganaar.equals("overzicht_overzicht"))
        {
            int verwijder = Integer.parseInt(request.getParameter("verwijder"));
            localbean.OnkostVerwijderen(verwijder);
            List onkosten = localbean.OpvragenWerknemer((int)sessie.getAttribute("wnr"));
            sessie.setAttribute("onkosten", onkosten);
            gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
        }
        
        if(ganaar.equals("krediet_overzicht"))
        {
            String keuze = request.getParameter("keuze");
            if(keuze.equals("Vorige"))
            {
                Onkosten onkost = localbean.tempOnkost((int)sessie.getAttribute("wnr"),Integer.parseInt(request.getParameter("bedrag")),request.getParameter("omschr"));
                request.setAttribute("gevraagdeonkost",onkost);
                gotoPage("JSP-Werknemer/status.jsp",request,response);
            }
            if(keuze.equals("Bevestig"))
            {
                if(Integer.parseInt(request.getParameter("bedrag"))!=0)
                {
                    localbean.OnkostToevoegen((int)sessie.getAttribute("wnr"),Integer.parseInt(request.getParameter("bedrag")),request.getParameter("omschr"),Integer.parseInt(request.getParameter("knr")),new Date(),Integer.parseInt(request.getParameter("status")));
                }
                gotoPage("JSP-Werknemer/overzicht.jsp",request,response);
            }
            
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
