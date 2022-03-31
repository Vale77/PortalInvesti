/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinClaseDocumento;
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
public class PrinClaseDocumentoFacade extends AbstractFacade<PrinClaseDocumento> implements PrinClaseDocumentoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinClaseDocumentoFacade() {
        super(PrinClaseDocumento.class);
    }

    @Override
    public List<PrinClaseDocumento> findClaseDocs(List<Short> clases) {
        Query q = em.createQuery("SELECT p FROM PrinClaseDocumento p WHERE p.clsCodigo IN :clases").setParameter("clases", clases);
        return q.getResultList();

    }

    @Override
    public List<PrinClaseDocumento> findBy(String modulo) {
        Query q = em.createNamedQuery("PrinClaseDocumento.findByClsModulo").setParameter("clsModulo", modulo);
        return q.getResultList();

    }

}
