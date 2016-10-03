/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityManager;

import entity.reservation.Reservation;
import javax.persistence.EntityManager;
import entity.reservation.Oeuvre;
import entity.utilisateur.Adherent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


public class ReservationManager extends AbstractEntityManager<Reservation>{

    public ReservationManager(Class<Reservation> entityClass) {
        super(entityClass);
    }
    
    public ReservationManager() {
        super(Reservation.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }
    
    //fontion personnalisée du manager 
    public boolean supprimerReservation(Adherent adherent, Oeuvre oeuvre){
        try {
            getUtx().begin();
            supprimerReservationNoTx(adherent,oeuvre);
            getUtx().commit();
            return true;
        } catch (NotSupportedException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getUtx().rollback();
        } catch (IllegalStateException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean supprimerReservationNoTx(Adherent adherent, Oeuvre oeuvre){
        try {
            getManager().joinTransaction();
            getManager().createQuery("DELETE from Reservation r "
                                                    + "WHERE r.oeuvre=:OeuvreArg "
                                                    + "AND r.adherent=:AdherentArg")
                         .setParameter("OeuvreArg", oeuvre)
                         .setParameter("AdherentArg", adherent)
                         .executeUpdate();//

            return true;
        }  catch (SecurityException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
