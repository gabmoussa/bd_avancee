/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reservation.Oeuvre;
import entity.reservation.Reservation;
import entity.utilisateur.Adherent;
import entity.utilisateur.CompteAdherent;
import entity.utilisateur.AdherentCompositePrimaryKey;
import entityManager.UserEntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;


public class UserController {
    private UserEntityManager adherentManager;
    
    public UserController(UserEntityManager manager){
        adherentManager = manager;
    }
    
    /**
     * 1.1
     * permet d'inscrire un adherent, renvoie le n° de carte adherent ou -1 si impossibilité de le créer (existre deja, arg null,...)
     * set le boolean a cotisé à false apar defaut 
     * @param adresse
     * @param prenom
     * @param nom
     * @param naissance
     * @param email
     * @return false si inscription ratée sinon true
     */
    public boolean subscribeAdherent(String adresse, String prenom, String nom,int anneeNaissance, int moisNaissance, int jourNaissance, String email, String password){
        //ne test pas le random mais ... 
        //bref je focus sur la persistence, ensuite je ferai ça si temps, sauf si tu lis ce message ^^
        Adherent adherent = new Adherent(adresse,prenom,nom, new Date(anneeNaissance,moisNaissance,jourNaissance),email,password);
        if(adherentManager.create(adherent)){
            Random random = new Random(233);
            adherent.setNumCarte(random.nextInt());
            //ajouter exception pour encadrer le tout, pk le edit peut aussi foirer. -> remonter ler exceptions
            adherentManager.edit(adherent);
        }
        return true;
    }
    
    /**
     * 1.1
     * recherche un adherent en utilisant sa clé primaire composée (nom,preonm,naissance)
     * @param nom
     * @param prenom
     * @param anneeNaissance
     * @param moisNaissance
     * @param jourNaissance
     * @return 
     */
    public Adherent retrieveAdherent(String nom, String prenom, int anneeNaissance, int moisNaissance, int jourNaissance){
        Calendar d = Calendar.getInstance();
        d.set(anneeNaissance, moisNaissance-1, jourNaissance);
        
        return adherentManager.find(new AdherentCompositePrimaryKey(nom,prenom,d.getTime()));
    }
    
    public Adherent chercherAdherent(String nom, String prenom, int anneeNaissance, int moisNaissance, int jourNaissance){
        List<Adherent> results = new ArrayList<Adherent>();
        
        //creation de la query
        Query query = this.adherentManager.getManager().createQuery("SELECT a FROM Adherent a "
                                                     + "WHERE a.nom= :NOM "
                                                     + "AND a.prenom= :PRENOM "
                                                     + "AND a.naissance= :DATE ");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("NOM", nom);
        query.setParameter("PRENOM", prenom);
        query.setParameter("DATE", new Date(anneeNaissance, moisNaissance, jourNaissance));
        results = query.getResultList();
        return results.size()==0?null:results.get(0);
    }
    
    public void ajouterCaution(String nom, String prenom, int anneeNaissance, int moisNaissance, int jourNaissance,int money){
        if(money>0){
            Adherent adherent = adherentManager.find(new AdherentCompositePrimaryKey(nom,prenom,new Date(anneeNaissance,moisNaissance,jourNaissance)));
            if(adherent!=null){
                CompteAdherent compte = adherent.getCompteAdherent();
                compte.setMontant(compte.getMontant()+money);
                adherent.setCompteAdherent(compte);
                //marche que si la cascade permet l'update!
                adherentManager.edit(adherent);
            }
        }
    }
    
    
    /**
     * 2.3
     * recherche les adhérents suivants les criteres, si doublons les laisses, surcharger equals et mettre dans coll supprimant les doublons
     * @param id
     * @return 
     *//*
    public List<Adherent> chercherAdherent(long id){
        List<Adherent> results = new ArrayList<Adherent>();
        
        //creation de la query
        Query query = adherentManager.getManager().createQuery("SELECT a FROM Adherent a "
                                                     + "WHERE a.id= :ID");
        //affecte les parametres de la query (comme si on donne des valeurs à des pseudo variables de la requete ":X")
        query.setParameter("ID", id);
        return query.getResultList();
    }*/
    
    //TODO
    /**
     * 1.2 - 1.3
     * TODO:initialiser le seuil avec un valeur recupéree en BD
     * @param nom
     * @param prenom
     * @param anneeNaissance
     * @param moisNaissance
     * @param jourNaissance
     * @param montant 
     */
   public void payerCotisation(String nom, String prenom, int anneeNaissance, int moisNaissance, int jourNaissance,int montant){
        if(montant>0){
            //TODO initialise cette variable avec la bonne valeur en cherchant en BD
            int seuil = 3;
            Adherent adherent = adherentManager.find(new AdherentCompositePrimaryKey(nom,prenom,new Date(anneeNaissance,moisNaissance,jourNaissance)));
            if(adherent!=null){
                CompteAdherent compte = adherent.getCompteAdherent();
                compte.setMontant(compte.getMontant()+montant);
                if(compte.getMontant()>seuil)
                    compte.setMontant(compte.getMontant()-seuil);
                adherent.setCompteAdherent(compte);

                //marche que si la cascade permet l'update!
                adherentManager.edit(adherent);
            }
        }
   }
   

   /**
    * 2.1
    * autehentifie un adherent
    * @param nom
    * @param prenom
    * @param anneeNaissance
    * @param motDePass
    * @param moisNaissance
    * @param jourNaissance
    * @return 
    */
   public boolean authenfierAdherent(String nom, String prenom, int anneeNaissance, String motDePass, int moisNaissance, int jourNaissance){
        Adherent adherent = adherentManager.find(new AdherentCompositePrimaryKey(nom,prenom,new Date(anneeNaissance,moisNaissance,jourNaissance)));
        if(adherent!=null){
            return adherent.getMotDePass().equals(motDePass);
        }
        return false;
   }
   
}
