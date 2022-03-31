/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.AnioAcademico;
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
public class AnioAcademicoFacade extends AbstractFacade<AnioAcademico> implements AnioAcademicoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnioAcademicoFacade() {
        super(AnioAcademico.class);
    }

    @Override
    public List<AnioAcademico> getListaAnios() {
        Query q = em.createNativeQuery("SELECT ANIO,NOMBRE_CICLO FROM interfaseOcu..CICLO_ACADEMICO",AnioAcademico.class);
        return q.getResultList();
    }
}
