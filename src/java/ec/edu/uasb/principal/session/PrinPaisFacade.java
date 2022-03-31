/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinPais;
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
public class PrinPaisFacade extends AbstractFacade<PrinPais> implements PrinPaisFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinPaisFacade() {
        super(PrinPais.class);
    }

    @Override
    public List<PrinPais> findAllorde() {
        Query q = em.createNativeQuery("select * from PRIN_PAIS WITH (NOLOCK)  ORDER BY PAI_NOMBRE asc", PrinPais.class);
        List<PrinPais> temp = q.getResultList();
        return temp;
    }
}
