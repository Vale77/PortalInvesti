/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinCiudad;
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
public class PrinCiudadFacade extends AbstractFacade<PrinCiudad> implements PrinCiudadFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinCiudadFacade() {
        super(PrinCiudad.class);
    }

    @Override
    public List<PrinCiudad> findAllorde(Short paiCodigo){
        Query q = em.createNativeQuery("select * from PRIN_CIUDAD WITH (NOLOCK) where PAI_CODIGO = ?  ORDER BY CIU_NOMBRE asc", PrinCiudad.class);
        q.setParameter(1, paiCodigo);
        List<PrinCiudad> temp = q.getResultList();
        return temp;
    }
}
