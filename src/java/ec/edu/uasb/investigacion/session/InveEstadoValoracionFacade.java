/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveRol;
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
public class InveEstadoValoracionFacade extends AbstractFacade<InveEstadoValoracion> implements InveEstadoValoracionFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveEstadoValoracionFacade() {
        super(InveEstadoValoracion.class);
    }

    @Override
    public List<InveEstadoValoracion> getEstadoBy(InveRol inveRol) {
        Query q = em.createQuery("SELECT i FROM InveEstadoValoracion i WHERE i.inveRol = :inveRol");
        q.setParameter("inveRol", inveRol);
        return q.getResultList();
    }
}
