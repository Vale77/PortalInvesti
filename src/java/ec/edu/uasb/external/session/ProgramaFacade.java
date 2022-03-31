/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.Programa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xavier.duque
 */
@Stateless
public class ProgramaFacade extends AbstractFacade<Programa> implements ProgramaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaFacade() {
        super(Programa.class);
    }

    @Override
    public List<Programa> getListaProgramasByAreaAnio(Short codArea, Short anio) {
        Query q = em.createNativeQuery("SELECT PRG_CODIGO, NOMBRE_PROGRAMA FROM interfaseOcu..PROGRAMA WHERE ARE_CODIGO = ? AND ANIO = ?", Programa.class);
        q.setParameter(1, codArea).setParameter(2, anio);
        return q.getResultList();
    }

    @Override
    public List<Programa> getListaProgramasByAreaAnio(Short codArea) {
        Query q = em.createNativeQuery("SELECT DISTINCT PRG_CODIGO, NOMBRE_PROGRAMA FROM interfaseOcu..PROGRAMA WHERE ARE_CODIGO = ?", Programa.class);
        q.setParameter(1, codArea);
        return q.getResultList();
    }
}
