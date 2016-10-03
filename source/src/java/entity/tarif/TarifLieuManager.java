/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tarif;

import entityManager.AbstractEntityManager;
import javax.persistence.EntityManager;

public class TarifLieuManager extends AbstractEntityManager<TarificationLieuHabitation>{

    public TarifLieuManager(Class<TarificationLieuHabitation> entityClass) {
        super(entityClass);
    }
    public TarifLieuManager() {
        super(TarificationLieuHabitation.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }
    
}
