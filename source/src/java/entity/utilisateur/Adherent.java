/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.utilisateur;

import entity.reservation.Emprunt;
import entity.reservation.Reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Adherent")
@IdClass(value=AdherentCompositePrimaryKey.class)
public class Adherent implements Serializable{
    
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private long idAdherent;
    
    @OneToMany(mappedBy = "adherent", cascade=CascadeType.ALL)
    @NotNull
    private List<Emprunt> emprunts;
    
    @OneToMany(mappedBy = "adherent",cascade=CascadeType.ALL)
    @NotNull
    private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "adherent",cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @NotNull
    private List<PreferenceAdherent> preferences; 
    
    //permet de lier la vie du compte à l'adherent.
    @OneToOne(cascade=CascadeType.ALL)
    private CompteAdherent compteAdherent;
    
    //clé composite
    @Id
    @Column(name="nom")
    @NotNull
    private String nom;
    
    @Id
    @Column(name="prenom")
    @NotNull
    private String prenom;
    
    @Id
    @Column(name="naissance")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date naissance;
    
    @Column(name="adhesion")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date adhesion;
    
    @Column(name="mail")
    @NotNull
    private String mail;
    
    @Column(name="numCarte")
    @NotNull
    private long numCarte;
    
    @Column(name="motDePass")
    @NotNull
    private String motDePass;
    
    @Column(name="aCotise")
    @NotNull
    private boolean aCotise;
    
    @Column(name="adresse")
    @NotNull
    private String adresse;

    
    public Adherent(){
        emprunts = new ArrayList<Emprunt>();
        reservations = new ArrayList<Reservation>();
        preferences = new ArrayList<PreferenceAdherent>();
        compteAdherent = new CompteAdherent();
        
        nom ="";
        prenom="";
        naissance=new Date();
        adhesion = new Date();
        mail = "";
        numCarte = 0;
        motDePass="";
        aCotise=false;
        adresse="";
    }
    
    public Adherent(String adresse, String prenom, String nom, Date naissance, String mail, String motDePass){
        this();
        this.adresse=adresse;
        this.nom=nom;
        this.prenom=prenom;
        this.naissance=naissance;
        this.mail=mail;
        this.motDePass=motDePass;
    }
    
    /**
     * @return the idAdherent
     */
    /*public long getIdAdherent() {
        return idAdherent;
    }*/

    /**
     * @param idAdherent the idAdherent to set
     */
    /*public void setIdAdherent(long idAdherent) {
        this.idAdherent = idAdherent;
    }*/

    /**
     * @return the emprunts
     */
    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    /**
     * @param emprunts the emprunts to set
     */
    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    /**
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * @return the preferences
     */
    public List<PreferenceAdherent> getPreferences() {
        return preferences;
    }

    /**
     * @param preferences the preferences to set
     */
    public void setPreferences(List<PreferenceAdherent> preferences) {
        this.preferences = preferences;
    }

    /**
     * @return the compteAdherent
     */
    public CompteAdherent getCompteAdherent() {
        return compteAdherent;
    }

    /**
     * @param compteAdherent the compteAdherent to set
     */
    public void setCompteAdherent(CompteAdherent compteAdherent) {
        this.compteAdherent = compteAdherent;
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

    /**
     * @return the adhesion
     */
    public Date getAdhesion() {
        return adhesion;
    }

    /**
     * @param adhesion the adhesion to set
     */
    public void setAdhesion(Date adhesion) {
        this.adhesion = adhesion;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the numCarte
     */
    public long getNumCarte() {
        return numCarte;
    }

    /**
     * @param numCarte the numCarte to set
     */
    public void setNumCarte(long numCarte) {
        this.numCarte = numCarte;
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
     * @return the aCotise
     */
    public boolean isaCotise() {
        return aCotise;
    }

    /**
     * @param aCotise the aCotise to set
     */
    public void setaCotise(boolean aCotise) {
        this.aCotise = aCotise;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
