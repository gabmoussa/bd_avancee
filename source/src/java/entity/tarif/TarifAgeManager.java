/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tarif;

import entityManager.AbstractEntityManager;
import javax.persistence.EntityManager;


public class TarifAgeManager extends AbstractEntityManager<TarificationAge>{

    public TarifAgeManager(Class<TarificationAge> entityClass) {
        super(entityClass);
    }
    public TarifAgeManager() {
        super(TarificationAge.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }
    
}
