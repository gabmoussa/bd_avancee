/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.reservation.Emprunt;
import entity.reservation.Item;
import entity.utilisateur.Adherent;
import entityManager.EmpruntEntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import utils.Utils;


public class EmpruntController {
    
    private EmpruntEntityManager empruntManager;
    
    public EmpruntController(EmpruntEntityManager manager){
        empruntManager=manager;
    }
    
    public List<Emprunt> chercherEmpruntParItem(Item item){
        //creation de la query
        Query query = empruntManager.getManager().createQuery("SELECT e FROM Emprunt e "
                                                     + "WHERE e.item=:ITEM ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ITEM", item);        
        return query.getResultList();
    }
    
// - list<Emprunts> getEmprunts(Adherent adhérent);
    public List<Emprunt> chercherEmpruntParAdherent(Adherent adherent){
        //creation de la query
        Query query = empruntManager.getManager().createQuery("SELECT e FROM Emprunt e "
                                                     + "WHERE e.adherent=:ADHERENT ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ADHERENT", adherent);        
        return query.getResultList();
    }
    /**
     * 3.6
     * Calcule combien de jour il reste avant la fin du delai autorisé d'un emprunt
     * @param emprunt  l'objet emprunt
     * @return nombre de jour restant 
     *        // 0 signifie que le delai est ecoulé que l'emprunt doit être rendu aujourdh'hui
     *        // -2 signifie que le delai est depassé de 2jour
     */
    
    //JAMAIS UTILISE ? 
    
 /*   public int delaiItem(Emprunt emprunt) {
        Calendar dateEmprunt = Utils.DateToCalendar(emprunt.getDateEmprunt());
        
        int nbeJourPossibledEmprunt = emprunt.getItem().getOeuvre().getTypeOeuvre().getDureeEmprunt();
        
        //Calcule de la date de rendu
        Calendar dateProgrammee = Calendar.getInstance();
        dateProgrammee.add(Calendar.DATE, nbeJourPossibledEmprunt);
           
        //Difference entre la date de rendu programmé et la date du jour
        return Utils.milisecondToDays(dateProgrammee.compareTo(dateEmprunt));

    }*/
    
}
