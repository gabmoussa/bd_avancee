/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.tarif.TarifAgeManager;
import entity.tarif.TarifLieuManager;
import entityManager.AdherentEntityManager;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.ReservationManager;


public class SuperController {
    private EmpruntEntityManager manager = new EmpruntEntityManager();
    private ItemEntityManager itemManager = new ItemEntityManager();
    private ReservationManager reservationManager = new ReservationManager();
    private AdherentEntityManager adherentManager = new AdherentEntityManager();
    private TarifLieuManager tarifLieuManager = new TarifLieuManager();
    private TarifAgeManager tarifAgeManager = new TarifAgeManager();
    
    public SuperController(){
        
    }
}
