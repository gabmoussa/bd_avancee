/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.utilisateur;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity 
@Table(name="CompteUtilisateur")
public class CompteUtilisateur implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUtilisateur;
    
    @Column(name="identifiantConnexion", unique=true)
    @NotNull
    private String identifiantConnexion;
    
    @Column(name="motDePass")
    @NotNull
    private String motDePass;

    public CompteUtilisateur(){
        motDePass="";
        identifiantConnexion="";
    }
    public CompteUtilisateur(String identCon, String mdp){
        motDePass=mdp;
        identifiantConnexion=identCon;
    }
    
    /**
     * @return the idUtilisateur
     */
    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * @param idUtilisateur the idUtilisateur to set
     */
    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * @return the motDePass
     */
    public String getMotDePass() {
        return motDePass;
    }

    /**
     * @param motDePass the motDePass to set
     */
    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    /**
     * @return the identifiantConnexion
     */
    public String getIdentifiantConnexion() {
        return identifiantConnexion;
    }

    /**
     * @param identifiantConnexion the identifiantConnexion to set
     */
    public void setIdentifiantConnexion(String identifiantConnexion) {
        this.identifiantConnexion = identifiantConnexion;
    }
}
