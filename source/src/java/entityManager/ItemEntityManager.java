/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityManager;

import entity.reservation.Item;
import javax.persistence.EntityManager;


public class ItemEntityManager extends AbstractEntityManager<Item> {

    public ItemEntityManager(Class<Item> entityClass) {
        super(entityClass);
    }
    
     public ItemEntityManager() {
        super(Item.class);
    }
     

    @Override
    protected EntityManager getEntityManager() {
        return getManager();
    }

    
}
