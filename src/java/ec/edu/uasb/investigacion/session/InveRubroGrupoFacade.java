/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
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
public class InveRubroGrupoFacade extends AbstractFacade<InveRubroGrupo> implements InveRubroGrupoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveRubroGrupoFacade() {
        super(InveRubroGrupo.class);
    }

    @Override
    public List<InveRubro> getRubrosXGrupo(Short grpCodigo) {
        Query q = em.createNamedQuery("InveRubroGrupo.findByGrpCodigo");
        q.setParameter("grpCodigo", grpCodigo);
        return q.getResultList();
    }

    @Override
    public List<InveRubro> getRubrosXGrupoXRegistrables(Short grpCodigo) {
        Query q = em.createNamedQuery("InveRubroGrupo.findByRegistrable");
        q.setParameter("grpCodigo", grpCodigo);
        return q.getResultList();
    }
}
