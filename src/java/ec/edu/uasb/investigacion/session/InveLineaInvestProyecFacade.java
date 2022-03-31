/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLineaInvestProyec;
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
public class InveLineaInvestProyecFacade extends AbstractFacade<InveLineaInvestProyec> implements InveLineaInvestProyecFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveLineaInvestProyecFacade() {
        super(InveLineaInvestProyec.class);
    }

    @Override
    public List<InveLineaInvestProyec> findBy(Short area) {
        Query q = em.createNamedQuery("InveLineaInvestProyec.findByLipAreaAcad").setParameter("lipAreaAcad", area);
        return q.getResultList();
    }

}
