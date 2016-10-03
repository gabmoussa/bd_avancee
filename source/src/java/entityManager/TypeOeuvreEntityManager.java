/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityManager;

import entity.reservation.TypeOeuvre;
import javax.persistence.EntityManager;


public class TypeOeuvreEntityManager extends AbstractEntityManager<TypeOeuvre> {

    public TypeOeuvreEntityManager(Class<TypeOeuvre> entityClass) {
        super(entityClass);
    }
    
     public TypeOeuvreEntityManager() {
        super(TypeOeuvre.class);
    } 

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
