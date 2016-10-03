/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display.jsp_servlet;

import controller.ItemSearcherController;
import entity.reservation.Item;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.ReservationManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;


@WebServlet(name = "RechercherOeuvreServlet", urlPatterns = {"/rechercherOeuvre"})
public class RechercherOeuvreServlet extends HttpServlet {

    @Resource
    private UserTransaction utx;

    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ItemEntityManager itemManager = new ItemEntityManager();
        itemManager.setEntityManager(emf.createEntityManager());
        itemManager.setUtx(utx);
        
        EntityManager entityManager = emf.createEntityManager();
        
        ReservationManager reservationManager = new ReservationManager();
        reservationManager.setEntityManager(entityManager);
        reservationManager.setUtx(utx);
        
        EmpruntEntityManager empruntManager = new EmpruntEntityManager();
        empruntManager.setEntityManager(emf.createEntityManager());
        empruntManager.setUserTransaction(utx);
        
        ItemSearcherController itemController = new ItemSearcherController(itemManager, reservationManager, empruntManager);

        
          //Recupere les donnes du forulaire
            String titre         = (String) request.getParameter("titre");
            String auteur  = (String) request.getParameter("auteur");
            String type = (String) request.getParameter("type");
            
            //Cherche l'item
            List<Item> listItems = new ArrayList<Item>();
            listItems =  itemController.chercherOeuvre(titre, auteur,type);
        try {
         request.setAttribute("listItems",listItems);          
            //Forward to the jsp page for rendering
            request.getRequestDispatcher("resultatRecherche.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } 

    }
    
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }

}
