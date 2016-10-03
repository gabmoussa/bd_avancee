/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reservation.Emprunt;
import entity.reservation.Item;
import entity.reservation.Oeuvre;
import entity.reservation.Reservation;
import entity.utilisateur.Adherent;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.ReservationManager;
import enumeration.Genre;
import enumeration.StatusItem;
import enumeration.TypePhysiqueOeuvre;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public class ItemSearcherController {
    private ItemEntityManager itemManager;
    private ReservationManager reservationManager;
    private EmpruntEntityManager empruntManager;
    
    public ItemSearcherController(ItemEntityManager manager, ReservationManager resManager, EmpruntEntityManager empManager){
        itemManager=manager;
        reservationManager=resManager;
        empruntManager=empManager;
    }
    
    private EntityManager getBasicManager(){
        return getItemManager().getManager();
    }
    
    private UserTransaction getUtx(){
        return getItemManager().getUtx();
    }
    
    /**
     * 2.3
     * recherche les oeuvres suivants les criteres, si doublons les laisses, surcharger equals et mettre dans coll supprimant les doublons
     * @param titre
     * @param autheur
     * @param typeOeuvre
     * @return 
     */
    public List<Item> chercherOeuvre(String titre, String autheur, String typeOeuvre){
        ArrayList<Item> results = new ArrayList<Item>();
        //creation de la query
       
        Query query = getBasicManager().createQuery("SELECT i FROM Item i, Oeuvre o "
                                                     + "WHERE i.oeuvre=o "
                                                     + "AND o.auteur= :AUTHEUR "
                                                     + "AND o.titre= :TITRE "
                                                     + "AND o.typeOeuvre IN :TYPEOEUVRE ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("AUTHEUR", autheur);
        query.setParameter("TITRE", titre);
        
         List<TypePhysiqueOeuvre> typeP = new ArrayList<TypePhysiqueOeuvre>();
        for(TypePhysiqueOeuvre g: EnumSet.allOf(TypePhysiqueOeuvre.class)){
            if (g.toString().equals(typeOeuvre))
                    typeP.add(g);
        }
        
        query.setParameter("TYPEOEUVRE", typeP);
        
        return query.getResultList();
    }
    
    /**
     * 2.3
     * recherche les oeuvres suivants les criteres, si doublons les laisses, surcharger equals et mettre dans coll supprimant les doublons
     * @param id
     * @return 
     */
    public List<Item> chercherOeuvre(long id){
        ArrayList<Item> results = new ArrayList<Item>();
        
        //creation de la query
        Query query = getBasicManager().createQuery("SELECT i FROM Item i, Oeuvre o "
                                                     + "WHERE i.oeuvre=o "
                                                     + "AND o.id= :ID");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ID", id);
        return query.getResultList();
    }
    // Item getDispo(int idOeuvre) -> retourne le premier item qu’elle trouve marchant avec l’oeuvre et étant disponible a l'emprunt
        /**
     * 2.3
     * recherche les oeuvres suivants les criteres, si doublons les laisses, surcharger equals et mettre dans coll supprimant les doublons
     * @param id
     * @return 
     */
    public Item chercherItemDispoParIdOeuvre(long id){
        List<Item> results = new ArrayList<Item>();
        //creation de la query
        Query query = getBasicManager().createQuery("SELECT i FROM Item i, Oeuvre o "
                                                     + "WHERE i.oeuvre=o "
                                                     + "AND i.status= :DISPONIBLE "
                                                     + "AND o.id= :ID");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ID", id);
        query.setParameter("DISPONIBLE", StatusItem.DISPONIBLE);
        results = query.getResultList();
        return results.size()==0?null:results.get(0);
    }
            /**
     * 
     * recherche item a partir de son id 
     * @param id
     * @return 
     */
    public Item chercherItem(long id){
        List<Item> results = new ArrayList<Item>();
        //creation de la query
        Query query = getBasicManager().createQuery("SELECT i FROM Item i "
                                                     + "WHERE i.idItem = :ID ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ID", id);
        results = query.getResultList();
        return results.size()==0?null:results.get(0);
    }

    
    /**
     * 2.3
     * recherche l'oeuvre ayant l'id id place en parametre
     * @param id
     * @return 
     *//*
    public Oeuvre chercherOeuvreParId(long id){
        ArrayList<Oeuvre> results = new ArrayList<Oeuvre>();
        
        //creation de la query
        Query query = getItemManager().getManager().createQuery("SELECT o FROM Oeuvre o "
                                                     + "WHERE o.id= :ID");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ID", id);
        results = query.getResultList();
        return results.size()==0?null:results.get(0);
    }*/
    
    /**
     * 2.5 3.1 3.3
     * emprunte une oeuvre ou pose une reservation dessus pour un futur emprunt
     * @param adherent
     * @param oeuvre
     * @return 1 si emprunté, 0 si reservé, -1 si rien fait
     */
    public int emprunterOeuvre(Adherent adherent, Oeuvre oeuvre){
       Emprunt emprunt;
       if(adherent == null || !adherent.isaCotise())
               return -1;//il n'a pas cotisé, on arrete
       try{
            getUtx().begin();
            getBasicManager().joinTransaction();
            reservationManager.getManager().joinTransaction();
            ///TODO DATE+tostring
            //List<Item> items = chercherOeuvre(oeuvre.getTitre(), oeuvre.getAuteur(), oeuvre.getGenreOeuvre().toString(), oeuvre.getDateCreation().toString());
            List<Item> items = chercherOeuvre(oeuvre.getTitre(), oeuvre.getAuteur(), oeuvre.getTypeOeuvre().toString());

            int size = items.size();
            boolean reserved = false;
            int it=0;
            while(it<size && !reserved){
                if(items.get(it).getStatus().equals(StatusItem.DISPONIBLE) || StatusItem.COMMANDE.equals(items.get(it).getStatus()))
                try{
                    getBasicManager().lock(items.get(it), LockModeType.PESSIMISTIC_READ);//plus personne ne peut y modifier l'entité, juste la lire
                    items.get(it).setStatus(StatusItem.EMPRUNTE);
                    items.get(it).setNbEmprunt(items.get(it).getNbEmprunt()+1);
                    emprunt = new Emprunt(adherent, items.get(it),new Date());
                    getBasicManager().persist(emprunt);//nested transaction not supported donc il ne faut pas imbriquer les transa
                    
                    reservationManager.supprimerReservationNoTx(adherent, oeuvre);
                    
                    //oeuvre adherent 
                    getBasicManager().lock(items.get(it), LockModeType.NONE);//plus personne ne peut y modifier l'entité, juste la lire
                    getUtx().commit();
                    return 1;//on a reservé
                }catch(Exception e){
                    
                }
                // on a pas reussi a reserver cet, item, on passe au suivant disponible
                it++;
            }
       }catch(Exception e){
                
       }
        try {
            getUtx().rollback();
        } catch (IllegalStateException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            //rien n'a marché, on ne devrait jamais arriver ici
       return -1;
    }
    
    public boolean reserverOeuvre(Adherent adherent, Oeuvre oeuvre){
        Reservation reservation = new Reservation(adherent, oeuvre);
        //on remplace l'ancienne reservation par celle ci (cas ou le delai de 10 jours va etre termine...l'adherent peut renouveler)
        reservationManager.supprimerReservation(adherent, oeuvre);
        //on va poser une reservation pour etre notifié plus tard de la disponibilité
        try{
            if(reservationManager.create(reservation))
            {
                //on a posé une futur reservation
                return true;
            }
           }catch(Exception e){}
           return false;
    }
    
     public boolean rendreItem(Adherent adherent, Item item){
        try {
            getUtx().begin();
            // changer status
            getItemManager().getManager().joinTransaction();
            empruntManager.getManager().joinTransaction();
            
            item.setStatus(StatusItem.DISPONIBLE);
            getItemManager().getManager().merge(item);
            
            // supprimer emprunt
            EmpruntController empruntController = new EmpruntController(empruntManager);
            List<Emprunt> emprunts = empruntController.chercherEmpruntParItem(item);
            if(emprunts.size()==0){
                getUtx().rollback();
                return false;
            }
            Emprunt emprunt = emprunts.get(0);
            empruntManager.getManager().remove(emprunt);
            getUtx().commit();
            return true;
        } catch (RollbackException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(ItemSearcherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
     }
    
    
    private boolean isEmptyNull(String o){ return o==null || "".equals(o);}

    /**
     * @return the itemManager
     */
    public ItemEntityManager getItemManager() {
        return itemManager;
    }

    /**
     * @param itemManager the itemManager to set
     */
    public void setItemManager(ItemEntityManager itemManager) {
        this.itemManager = itemManager;
    }
}
