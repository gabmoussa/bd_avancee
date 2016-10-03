/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.reservation;

import enumeration.Genre;
import enumeration.TypePhysiqueOeuvre;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Oeuvre")
public class Oeuvre implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(name = "dateCreation")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    
    @Column(name="genreOeuvre")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genreOeuvre;
    
    @Column(name = "auteur")
    @NotNull
    private String auteur;
    
    @Column(name = "titre")
    @NotNull
    private String titre;
    
    /*@ManyToOne(cascade=CascadeType.PERSIST)
    @NotNull
    private TypeOeuvre typeOeuvre;*/
    @Column(name="typeOeuvre")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypePhysiqueOeuvre typeOeuvre;
    
    
  
    public Oeuvre(){
        dateCreation=new Date();
        genreOeuvre=Genre.aventure;
        auteur="default auteur";
        titre="default titre";
        //typeOeuvre= new TypeOeuvre();
        typeOeuvre = TypePhysiqueOeuvre.CD;
    }

    
    public Oeuvre(TypePhysiqueOeuvre type){
        dateCreation=new Date();
        genreOeuvre=Genre.aventure;
        auteur="";
        titre="";
        typeOeuvre= type;
    }

    public TypePhysiqueOeuvre getTypeOeuvre() {
        return typeOeuvre;
    }

    public void setTypeOeuvre(TypePhysiqueOeuvre typeOeuvre) {
        this.typeOeuvre = typeOeuvre;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return the genreOeuvre
     */
    public Genre getGenreOeuvre() {
        return genreOeuvre;
    }

    /**
     * @param genreOeuvre the genreOeuvre to set
     */
    public void setGenreOeuvre(Genre genreOeuvre) {
        this.genreOeuvre = genreOeuvre;
    }

    /**
     * @return the auteur
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * @param auteur the auteur to set
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    
}
