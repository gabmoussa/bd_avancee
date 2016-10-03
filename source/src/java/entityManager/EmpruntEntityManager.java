/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityManager;

import entityManagerInterface.EmpruntManagerInterface;
import entity.reservation.Emprunt;
import javax.persistence.EntityManager;




public class EmpruntEntityManager extends AbstractEntityManager<Emprunt> implements EmpruntManagerInterface{

    public EmpruntEntityManager() {
        super(Emprunt.class);
    }
    
    public EmpruntEntityManager(Class<Emprunt> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }

    public void persistEmprunt(Emprunt emprunt) {
        create(emprunt);
    }

    public Emprunt findEmprunt(Object id) {
        return  find(id);
    }

    public void removeEmprunt(Emprunt emprunt) {
        remove(emprunt);
    }


    
}
