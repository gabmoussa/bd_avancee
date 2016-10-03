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
@Table(name="CompteAdherent")
public class CompteAdherent implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCompte;
    
    @Column(name="montant")
    @NotNull
    private int montant;
    
    public CompteAdherent(){
        montant = 0;
    }

    /**
     * @return the idCompte
     */
    public long getIdCompte() {
        return idCompte;
    }

    /**
     * @param idCompte the idCompte to set
     */
    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    /**
     * @return the montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
}
