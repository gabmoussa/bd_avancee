/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entityManager.AbstractEntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;


public class FactoryManagerCreator {
    private static EntityManager em=null;
    private static String unitPersistence="web-jpaPU";
    
    public static EntityManagerFactory createFactoryManager(){
        return Persistence.createEntityManagerFactory(unitPersistence);
    }
    
    public static EntityManager getEntityManager(){
        if(em==null){
            try{
                em=Persistence.createEntityManagerFactory(unitPersistence).createEntityManager();
            }catch(Exception e){ 
                Logger.getLogger(FactoryManagerCreator.class.getName()).log(Level.SEVERE, "impossible de creer le entity factory manager"+unitPersistence, e);
            }
        }
        return em;
    }
}
