/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.reservation;

import entity.utilisateur.Adherent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Emprunt")
public class Emprunt implements Serializable{//doit mettre serializable, tester et enlever??
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long idEmprunt;
    
    @NotNull
    @Column(name = "dateEmprunt")
    @Temporal(TemporalType.DATE)
    private Date dateEmprunt;
    
    @NotNull
    private Adherent adherent; 
    
    
    @NotNull
    private Item item;
    
    public Emprunt(){
        dateEmprunt=new Date(0,0,0);
        adherent = new Adherent();
        item = new Item();
    }
    

    public Emprunt(Adherent adh, Item it, Date date){
        dateEmprunt = date;
        adherent = adh;
        item = it;
    }
    
    public Emprunt(Item theItem){
        dateEmprunt=Calendar.getInstance().getTime();
        adherent = new Adherent();
        item = theItem;
    }
    
    public long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    /**
     * @return the dateEmprunt
     */
    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * @param dateEmprunt the dateEmprunt to set
     */
    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    
    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
    
    
    
    
}
