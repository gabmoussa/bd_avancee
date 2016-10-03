/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package display.jsp_servlet;

import controller.EmpruntController;
import controller.ItemSearcherController;
import controller.UserController;
import entity.reservation.Item;
import entity.reservation.Oeuvre;
import entity.utilisateur.Adherent;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.ReservationManager;
import entityManager.UserEntityManager;
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
import utils.Utils;


@WebServlet(name = "RendreEmprunt", urlPatterns = {"/rendreEmprunt"})
public class RendreEmprunt extends HttpServlet {

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
        
         UserEntityManager userEntityManager = new UserEntityManager();
         userEntityManager.setEntityManager(emf.createEntityManager());
         userEntityManager.setUserTransaction(utx);
        
         UserController userController = new UserController(userEntityManager);
         
         //-- Recuperer adherent courant
         String nom = (String) request.getParameter("adherentNom");
         String prenom = (String) request.getParameter("adherentPrenom");
         String adherentDate = (String) request.getParameter("adherentDate");
         Integer[] dateDecoupe = Utils.decoupeDateModeleDate(adherentDate);
         
        Adherent adherent = userController.retrieveAdherent(nom,prenom,dateDecoupe[0].intValue(),dateDecoupe[1].intValue(),dateDecoupe[2].intValue());     

       
         
        //--- Recuper les oeuvre cochees
        boolean reussi = true;
        String[] listItemCheckedARetourner = request.getParameterValues("check");
        List<Oeuvre> listOeuvreARetourner = new ArrayList<Oeuvre>();

        //--- emprunter Oeuvre 
        for (String idItem : listItemCheckedARetourner) {
            
            Item itemCurrent = itemController.chercherItem(Long.valueOf(idItem));
            if(!itemController.rendreItem(adherent,itemCurrent)){
             reussi = false;
             }
        }

        try {
            request.setAttribute("result", reussi);   
            //Forward to the jsp page for rendering
            request.getRequestDispatcher("rendreEmprunt.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } 

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }

}

