/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.utilisateur;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="PreferenceAdherent")
public class PreferenceAdherent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="preference")
    private String preference;
    
    @ManyToOne
    @NotNull
    private Adherent adherent; 
        
    public PreferenceAdherent(){
        preference = "";
        adherent = new Adherent();
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
     * @return the preference
     */
    public String getPreference() {
        return preference;
    }

    /**
     * @param preference the preference to set
     */
    public void setPreference(String preference) {
        this.preference = preference;
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
