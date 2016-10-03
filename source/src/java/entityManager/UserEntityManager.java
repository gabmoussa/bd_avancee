/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityManager;

import entity.utilisateur.Adherent;
import javax.persistence.EntityManager;


public class UserEntityManager extends AbstractEntityManager<Adherent>{

    public UserEntityManager() {
        super(Adherent.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }
    

    
}
