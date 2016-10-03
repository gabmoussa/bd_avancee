/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reservation.Reservation;
import entity.reservation.Oeuvre;
import entity.utilisateur.Adherent;
import entityManager.ReservationManager;
import java.util.List;
import javax.persistence.Query;


public class ReservationController {
    private ReservationManager reservationManager;
    
    public ReservationController(ReservationManager manager){
        this.reservationManager=manager;
    }
    
    public List<Reservation> chercherReservationParOeuvre(Oeuvre oeuvre){
        //creation de la query
        Query query = reservationManager.getManager().createQuery("SELECT r FROM Reservation r "
                                                     + "WHERE r.oeuvre=:OEUVRE ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("OEUVRE", oeuvre);        
        return query.getResultList();
    }
    
// - list<Emprunts> getEmprunts(Adherent adhérent);
    public List<Reservation> chercherReservationParAdherent(Adherent adherent){
        //creation de la query
        Query query = reservationManager.getManager().createQuery("SELECT r FROM Reservation r "
                                                     + "WHERE r.adherent=:ADHERENT ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ADHERENT", adherent);        
        return query.getResultList();
    }
}
