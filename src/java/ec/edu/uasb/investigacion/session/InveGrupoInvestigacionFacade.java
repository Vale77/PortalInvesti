/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
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
public class InveGrupoInvestigacionFacade extends AbstractFacade<InveGrupoInvestigacion> implements InveGrupoInvestigacionFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveGrupoInvestigacionFacade() {
        super(InveGrupoInvestigacion.class);
    }

    @Override
    public List<InveGrupoInvestigacion> findAll() {
        Query q = em.createQuery("SELECT i FROM InveGrupoInvestigacion i ORDER BY i.ginNombre");
        return q.getResultList();
    }

}
