/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class PrinPersonaFacade extends AbstractFacade<PrinPersona> implements PrinPersonaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinPersonaFacade() {
        super(PrinPersona.class);
    }

    @Override
    public PrinPersona findByCedula(String cedPass) {
        PrinPersona p = null;
        Query q = em.createNamedQuery("PrinPersona.findByPerIdDoc").setHint("eclipselink.refresh", true).setParameter("perIdDoc", cedPass);
        try {
            p = (PrinPersona) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return p;
    }

    @Override
    public List<PrinPersona> findByApellidos(String apellido) {
        Query q = em.createNativeQuery("SELECT * FROM PRIN_PERSONA where PER_PRIMER_APELLIDO+ISNULL(' '+ "
                + "PER_SEGUNDO_APELLIDO,'') LIKE ? ORDER BY PER_PRIMER_APELLIDO, PER_SEGUNDO_APELLIDO, PER_NOMBRES ", PrinPersona.class);
        q.setParameter(1, apellido + "%");
        return q.getResultList();
    }

    @Override
    public List<PrinPersona> findByApellidosNombres(String apellido, String nombre) {
        Query q = em.createNativeQuery("SELECT * FROM PRIN_PERSONA"
                + " where PER_PRIMER_APELLIDO+ISNULL(' '+ PER_SEGUNDO_APELLIDO,'') LIKE ? AND PER_NOMBRES LIKE ? "
                + " ORDER BY PER_PRIMER_APELLIDO, PER_SEGUNDO_APELLIDO, PER_NOMBRES ", PrinPersona.class);
        q.setParameter(1, apellido + "%");
        q.setParameter(2, nombre + "%");
        return q.getResultList();
    }

    @Override
    public void create(PrinPersona entity) {
        super.create(entity);
        em.flush();
        PrinTitulo titulo = entity.getPrinTitulo();
        if (titulo != null) {
            titulo.setPrinPersona(entity);
            em.persist(titulo);
            entity.setPrinTituloCollection(new ArrayList<PrinTitulo>());
            entity.getPrinTituloCollection().add(titulo);
        }
    }

}
