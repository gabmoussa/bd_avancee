/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityManager;



import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public abstract class AbstractEntityManager<T> {
    
    private Class<T> entityClass;
    
    //TODO ajouter les transactions begin ... end + tester l'injection
    @Resource
    private UserTransaction utx;

    @PersistenceContext
    private EntityManager manager;

    @PersistenceUnit
    protected EntityManagerFactory emf;
    
    public AbstractEntityManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    public void setEntityManager(EntityManager m){ setManager(m);}
    public void setUserTransaction(UserTransaction utxArg){setUtx(utxArg);}
    
    public boolean create(T entity) {
         try {
            getUtx().begin();
            getEntityManager().joinTransaction();
            getEntityManager().persist(entity);
            getUtx().commit();
            return true;
        } catch (NotSupportedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }

    public void edit(T entity) {
        try {
            getUtx().begin();
            getManager().joinTransaction();
            getEntityManager().merge(entity);
            getUtx().commit();
        } catch (NotSupportedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void remove(T entity)  {
        try {
            getUtx().begin();
            T t = entity;//getEntityManager().merge(entity);
            getManager().joinTransaction();
            getManager().lock(t, LockModeType.PESSIMISTIC_READ); //possiblr que dans une transaction
            getEntityManager().remove(t);
            getUtx().commit();
        } catch (NotSupportedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, "remove impossible", ex);
        } catch (SystemException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, "remove impossible : System exception", ex);
        } catch (RollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AbstractEntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    public T find(Object id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * @return the entityClass
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @param entityClass the entityClass to set
     */
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @return the manager
     */
    public EntityManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @return the utx
     */
    public UserTransaction getUtx() {
        return utx;
    }

    /**
     * @param utx the utx to set
     */
    public void setUtx(UserTransaction utx) {
        this.utx = utx;
    }
}