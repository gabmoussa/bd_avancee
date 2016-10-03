/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityManager;

import entity.reservation.Oeuvre;
import javax.persistence.EntityManager;


public class OeuvreEntityManager extends AbstractEntityManager<Oeuvre> {

    public OeuvreEntityManager(Class<Oeuvre> entityClass) {
        super(entityClass);
    }
    
     public OeuvreEntityManager() {
        super(Oeuvre.class);
    } 

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
