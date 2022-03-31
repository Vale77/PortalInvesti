/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
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
public class InveObjetivoSocioEconomicoFacade extends AbstractFacade<InveObjetivoSocioEconomico> implements InveObjetivoSocioEconomicoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveObjetivoSocioEconomicoFacade() {
        super(InveObjetivoSocioEconomico.class);
    }

    @Override
    public List<InveObjetivoSocioEconomico> findAll() {
        Query q = em.createNamedQuery("InveObjetivoSocioEconomico.findAll");
        return q.getResultList();
    }
}
