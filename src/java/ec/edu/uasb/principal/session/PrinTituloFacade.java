/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
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
public class PrinTituloFacade extends AbstractFacade<PrinTitulo> implements PrinTituloFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinTituloFacade() {
        super(PrinTitulo.class);
    }
    
    @Override
    public List<PrinTitulo> findTitulosBy(PrinPersona persona){
        Query q = em.createQuery("SELECT p FROM PrinTitulo p WHERE p.prinPersona = :persona and p.esPrincipal = :esPrincipal");
        q.setParameter("persona", persona).setParameter("esPrincipal", "S");
        return q.getResultList();
    }
}
