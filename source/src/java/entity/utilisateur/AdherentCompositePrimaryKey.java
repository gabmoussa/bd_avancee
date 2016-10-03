/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.utilisateur;

import java.io.Serializable;
import java.util.Date;


public class AdherentCompositePrimaryKey implements Serializable{
    private String prenom;
    private String nom;
    private Date naissance;
    
    public AdherentCompositePrimaryKey(){
        prenom="";
        nom="";
        naissance=new Date();
    }
    public AdherentCompositePrimaryKey(String nom, String prenom, Date naissance){
        this.prenom=prenom;
        this.nom=nom;
        this.naissance=naissance;
    }
    @Override
    public int hashCode() {
        return getPrenom().hashCode() + getNom().hashCode() + getNaissance().hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AdherentCompositePrimaryKey){
            AdherentCompositePrimaryKey adhPk = (AdherentCompositePrimaryKey) obj;
 
            if(!adhPk.getNaissance().equals(naissance)){
                return false;
            }
            if(!adhPk.getNom().equals(nom)){
                return false;
            }
            if(!adhPk.getPrenom().equals(prenom)){
                return false;
            }
 
            return true;
        }
 
        return false;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the naissance
     */
    public Date getNaissance() {
        return naissance;
    }

    /**
     * @param naissance the naissance to set
     */
    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}
