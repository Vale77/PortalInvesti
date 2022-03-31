/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
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
public class InveAmbitoGeograficoFacade extends AbstractFacade<InveAmbitoGeografico> implements InveAmbitoGeograficoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveAmbitoGeograficoFacade() {
        super(InveAmbitoGeografico.class);
    }

    @Override
    public List<InveAmbitoGeografico> findAll() {
        Query q = em.createNamedQuery("InveAmbitoGeografico.findAll");
        return q.getResultList();
    }
}
