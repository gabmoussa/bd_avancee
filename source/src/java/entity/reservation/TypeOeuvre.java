/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.reservation;

import enumeration.TypePhysiqueOeuvre;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

 @Entity
 @Table(name="TypeOeuvre")
public class TypeOeuvre implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTypeOeuvre;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "typePhysiqueOeuvre")
    @NotNull
    private TypePhysiqueOeuvre type;
    
    /**
     * Duree d'emprunt possible 
     * unité : journée
     */
    @Column(name = "dureeEmprunt")
    @NotNull
    //@Temporal(TemporalType.DATE)
    private int dureeEmprunt;
    
    public TypeOeuvre(){
        type = TypePhysiqueOeuvre.LIVRE;
        dureeEmprunt = 1;
    }
    
    public  TypeOeuvre(TypePhysiqueOeuvre typeOeuvre,int duree){
        type = typeOeuvre;
        dureeEmprunt = duree;
    }
    
    public TypeOeuvre(TypePhysiqueOeuvre to){
        type = to;
        dureeEmprunt = 1;
    }
    /**
     * @return the idTypeOeuvre
     */
    public long getIdTypeOeuvre() {
        return idTypeOeuvre;
    }

    /**
     * @param idTypeOeuvre the idTypeOeuvre to set
     */
    public void setIdTypeOeuvre(long idTypeOeuvre) {
        this.idTypeOeuvre = idTypeOeuvre;
    }

    /**
     * @return the type
     */
    public TypePhysiqueOeuvre getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypePhysiqueOeuvre type) {
        this.type = type;
    }

    /**
     * @return the dureeEmprunt
     */
    public int getDureeEmprunt() {
        return dureeEmprunt;
    }

    /**
     * @param dureeEmprunt the dureeEmprunt to set
     */
    public void setDureeEmprunt(int dureeEmprunt) {
        this.dureeEmprunt = dureeEmprunt;
    }

}

//A la suppression, mettre un trigger pour supprimer toutes les oeuvres+item liées au typeOeuvre supprimé
