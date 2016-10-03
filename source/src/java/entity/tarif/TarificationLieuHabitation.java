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
import javax.validation.constraints.NotNull;


@Entity 
@Table(name = "TarificationLieuHabitation")
public class TarificationLieuHabitation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTarificationLieu;
    
    @Column(name="distanceSeuil")
    @NotNull
    private int distanceSeuil;
    
    @Column(name="prixFonctionDistances")
    @NotNull
    private int prixFonctionDistance;

    public TarificationLieuHabitation(){
        distanceSeuil=0;
        prixFonctionDistance=0;
    }
    
    public TarificationLieuHabitation(int distance, int prix){
        distanceSeuil=distance;
        prixFonctionDistance=prix;
    }
    
        
    /**
     * @return the idTarificationLieu
     */
    public long getIdTarificationLieu() {
        return idTarificationLieu;
    }

    /**
     * @param idTarificationLieu the idTarificationLieu to set
     */
    public void setIdTarificationLieu(long idTarificationLieu) {
        this.idTarificationLieu = idTarificationLieu;
    }

    /**
     * @return the distanceSeuil
     */
    public int getDistanceSeuil() {
        return distanceSeuil;
    }

    /**
     * @param distanceSeuil the distanceSeuil to set
     */
    public void setDistanceSeuil(int distanceSeuil) {
        this.distanceSeuil = distanceSeuil;
    }

    /**
     * @return the prixFonctionDistance
     */
    public int getPrixFonctionDistance() {
        return prixFonctionDistance;
    }

    /**
     * @param prixFonctionDistance the prixFonctionDistance to set
     */
    public void setPrixFonctionDistance(int prixFonctionDistance) {
        this.prixFonctionDistance = prixFonctionDistance;
    }
}
