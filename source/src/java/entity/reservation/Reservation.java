/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.reservation;

import entity.utilisateur.Adherent;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Reservation")
public class Reservation implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long IdReservation;
    
    @Column(name="dateReservation")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateRerservation;
    
    @Column(name="dateMiseADisposition")
    @Temporal(TemporalType.DATE)
    private Date dateMiseADisposition;
    
    @ManyToOne
    private Oeuvre oeuvre;

    @ManyToOne
    @NotNull
    private Adherent adherent; 
        
    public Reservation() {
        dateRerservation = new Date();
        dateMiseADisposition = new Date();
        oeuvre = new Oeuvre();
        adherent = new Adherent();
    }
    
    public Reservation(Adherent adherent, Oeuvre oeuvre) {
        dateRerservation = new Date();
        dateMiseADisposition = null;
        this.oeuvre = oeuvre;
        this.adherent = adherent;
    }
    
    /**
     * @return the IdReservation
     */
    public long getIdReservation() {
        return IdReservation;
    }

    /**
     * @param IdReservation the IdReservation to set
     */
    public void setIdReservation(long IdReservation) {
        this.IdReservation = IdReservation;
    }

    /**
     * @return the dateRerservation
     */
    public Date getDateRerservation() {
        return dateRerservation;
    }

    /**
     * @param dateRerservation the dateRerservation to set
     */
    public void setDateRerservation(Date dateRerservation) {
        this.dateRerservation = dateRerservation;
    }

    /**
     * @return the dateMiseADisposition
     */
    public Date getDateMiseADisposition() {
        return dateMiseADisposition;
    }

    /**
     * @param dateMiseADisposition the dateMiseADisposition to set
     */
    public void setDateMiseADisposition(Date dateMiseADisposition) {
        this.dateMiseADisposition = dateMiseADisposition;
    }

    /**
     * @return the oeuvre
     */
    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    /**
     * @param oeuvre the oeuvre to set
     */
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    /**
     * @return the adherent
     */
    public Adherent getAdherent() {
        return adherent;
    }

    /**
     * @param adherent the adherent to set
     */
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}
