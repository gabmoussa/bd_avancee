/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.tarif.TarifAgeManager;
import entity.tarif.TarifLieuManager;
import entity.tarif.TarificationAge;
import entity.tarif.TarificationLieuHabitation;
import entity.utilisateur.Adherent;
import java.util.List;


public class TarifController {
    TarifLieuManager tarifLieuManager = new TarifLieuManager();
    TarifAgeManager tarifAgeManager = new TarifAgeManager();
    //Controller userController = new UserController(null);
    
    public TarifController(TarifLieuManager tlm, TarifAgeManager tam){
        tarifAgeManager=tam;
        tarifLieuManager=tlm;
        //userController=uc;
    }
    
    public int getTarif(String nom, String prenom,int anneeNaissance, int moisNaissance, int jourNaissance){
        //Adherent adh = userController.retrieveAdherent(nom, prenom, anneeNaissance, moisNaissance, jourNaissance);
        List<TarificationAge>  tarifsAge= tarifAgeManager.findAll();//peut mettre lazy mais peu de donnée dans ces tables ...
        List<TarificationLieuHabitation> tarifsLieu = tarifLieuManager.findAll();
        int prixAge=0;
        int prixLieu=0;
        for(TarificationAge t : tarifsAge){
            if(t.getAgeSeuil()<anneeNaissance)
                prixAge= java.lang.Math.max(prixAge,t.getPrixFonctionAge());
        }
        for(TarificationLieuHabitation t : tarifsLieu){
            if(true) //TODO implementer un vrai test
                prixLieu= java.lang.Math.max(prixLieu,t.getPrixFonctionDistance());
        }
        return prixAge+prixLieu;
    }
    public boolean verifierCautionSuffisant(int nbReservations, Adherent adherent){
        return true;
    }
}
