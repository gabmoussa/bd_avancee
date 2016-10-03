/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.reservation;

import enumeration.StatusItem;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Item")
public class Item implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idItem;
    
    @NotNull
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Oeuvre oeuvre;
    
    @NotNull
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE} )
    @Enumerated(EnumType.STRING)
    private StatusItem status;
    
    @NotNull
    @Column(name = "nbEmprunt")
    private int nbEmprunt;

    public Item() {
        oeuvre = new Oeuvre();
        status = StatusItem.DISPONIBLE;
        nbEmprunt=0;
    }
    
    public Item(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        status = StatusItem.DISPONIBLE;
        nbEmprunt=0;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public int getNbEmprunt() {
        return nbEmprunt;
    }

    public void setNbEmprunt(int nbEmprunt) {
        this.nbEmprunt = nbEmprunt;
    }


    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }
    
}
