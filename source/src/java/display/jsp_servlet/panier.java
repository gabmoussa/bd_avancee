/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display.jsp_servlet;

import controller.EmpruntController;
import controller.ItemSearcherController;
import controller.ReservationController;
import controller.UserController;
import entity.reservation.Emprunt;
import entity.reservation.Item;
import entity.reservation.Reservation;
import entity.utilisateur.Adherent;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.ReservationManager;
import entityManager.UserEntityManager;
import enumeration.StatusItem;
import java.io.IOException;
import java.util.HashMap;
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


@WebServlet(name = "panier", urlPatterns = {"/panier"})
public class panier extends HttpServlet {

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

        ReservationController reservationController = new ReservationController(reservationManager);

        //-- Recuperer adherent courant
        //james = new Adherent("120 dans un carton, sous un pont", "james", "cam", new Date(1992,01,27)
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String date = (String) request.getParameter("date");
        Integer[] dateDecoupe = Utils.decoupeDate(date);
        //Adherent adherent = userController.retrieveAdherent("cam","james",1992,01,27);
        Adherent adherent = userController.retrieveAdherent(nom, prenom, dateDecoupe[0].intValue(), dateDecoupe[1].intValue(), dateDecoupe[2].intValue());

        HashMap<Long, StatusItem> mapOeuvreDispo = new HashMap<Long, StatusItem>();
        List<Reservation> listReservation = reservationController.chercherReservationParAdherent(adherent);
        Item itemOeuvreCurrent;
        for (Reservation resa : listReservation) {
           
            itemOeuvreCurrent = itemController.chercherItemDispoParIdOeuvre(resa.getOeuvre().getId());

            if (itemOeuvreCurrent != null) {
                mapOeuvreDispo.put(resa.getOeuvre().getId(), StatusItem.DISPONIBLE);
            } else {
                mapOeuvreDispo.put(resa.getOeuvre().getId(), StatusItem.EMPRUNTE);
            }
        }

        try {
            request.setAttribute("listReservation", listReservation);
            request.setAttribute("mapOeuvrDispo", mapOeuvreDispo);
            request.setAttribute("adherent", adherent);
            //Forward to the jsp page for rendering
            request.getRequestDispatcher("panier.jsp").forward(request, response);
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
