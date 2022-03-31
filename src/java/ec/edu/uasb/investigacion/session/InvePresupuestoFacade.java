/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveRubro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InvePresupuestoFacade extends AbstractFacade<InvePresupuesto> implements InvePresupuestoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvePresupuestoFacade() {
        super(InvePresupuesto.class);
    }

    @Override
    public List<InvePresupuesto> getPresupuestoBy(InveRubro inveRubro) {
        Query q = em.createQuery("SELECT i FROM InvePresupuesto i WHERE i.inveRubro = :inveRubro");
        q.setParameter("inveRubro", inveRubro);
        return q.getResultList();
    }
    
}
