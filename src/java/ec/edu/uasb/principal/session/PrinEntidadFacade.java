/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinEntidad;
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
public class PrinEntidadFacade extends AbstractFacade<PrinEntidad> implements PrinEntidadFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinEntidadFacade() {
        super(PrinEntidad.class);
    }

    @Override
    public List<PrinEntidad> findAll() {
        Query q = em.createQuery("SELECT p FROM PrinEntidad p ORDER BY p.entNombre ").setHint("eclipselink.refresh", true);
        return q.getResultList();
    }
    
    @Override
    public List<PrinEntidad> findAllUniversidades() {
        Query q = em.createQuery("SELECT p FROM PrinEntidad p WHERE p.prinTipoEntidad.tenCodigo = 6 ORDER BY p.entNombre").setHint("eclipselink.refresh", true);
        return q.getResultList();
    }

}
