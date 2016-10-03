/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tarif;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.resource.spi.AuthenticationMechanism;
import javax.validation.constraints.NotNull;


@Entity 
@Table(name = "TarificationAge")
public class TarificationAge {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long IdTafificationAge;
    
    @Column(name="ageSeuil")
    @NotNull
    private int ageSeuil;
    
    @Column(name="prixFonctionAge")
    @NotNull
    private int prixFonctionAge;
    
    public TarificationAge(){
        ageSeuil = 0;
        prixFonctionAge = 0;
    }
    
    public TarificationAge(int age, int prix){
        ageSeuil = age;
        prixFonctionAge = prix;
    }

    /**
     * @return the IdTafificationAge
     */
    public long getIdTafificationAge() {
        return IdTafificationAge;
    }

    /**
     * @param IdTafificationAge the IdTafificationAge to set
     */
    public void setIdTafificationAge(long IdTafificationAge) {
        this.IdTafificationAge = IdTafificationAge;
    }

    /**
     * @return the ageSeuil
     */
    public int getAgeSeuil() {
        return ageSeuil;
    }

    /**
     * @param ageSeuil the ageSeuil to set
     */
    public void setAgeSeuil(int ageSeuil) {
        this.ageSeuil = ageSeuil;
    }

    /**
     * @return the prixFonctionAge
     */
    public int getPrixFonctionAge() {
        return prixFonctionAge;
    }

    /**
     * @param prixFonctionAge the prixFonctionAge to set
     */
    public void setPrixFonctionAge(int prixFonctionAge) {
        this.prixFonctionAge = prixFonctionAge;
    }
}
