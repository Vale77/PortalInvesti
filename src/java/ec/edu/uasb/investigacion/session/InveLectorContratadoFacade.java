/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLectorContratado;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.util.Date;
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
public class InveLectorContratadoFacade extends AbstractFacade<InveLectorContratado> implements InveLectorContratadoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveLectorContratadoFacade() {
        super(InveLectorContratado.class);
    }

    @Override
    public InveLectorContratado findById(String identificacion) {
        InveLectorContratado lc = null;
        Query q = em.createQuery("select v from InveLectorContratado v where v.prinPersona.perIdDoc = :identificacion").setParameter("identificacion", identificacion);
        try {
            lc = (InveLectorContratado) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return lc;
    }

    @Override
    public void createLectorContratado(PrinPersona persona, PrinTitulo prinTitulo) {
        InveLectorContratado lectorContratado = new InveLectorContratado();
        if (persona.getPerCodigo() == null) {
            em.persist(persona);
            em.flush();
        } else {
            em.merge(persona);
        }
        if (prinTitulo.getTitCodigo() == null) {
            prinTitulo.setPrinPersona(persona);
            prinTitulo.setFechaRegistro(new Date());
            em.persist(prinTitulo);
        }

        lectorContratado.setPrinPersona(persona);
        lectorContratado.setPerCodigo(persona.getPerCodigo());
        lectorContratado.setLcoUsuarioCrea(persona.getPerIdDoc());
        lectorContratado.setLcoFechaCrea(new Date());
        super.create(lectorContratado);
    }

}
