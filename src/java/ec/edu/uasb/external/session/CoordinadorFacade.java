/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.Coordinador;
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
public class CoordinadorFacade extends AbstractFacade<Coordinador> implements CoordinadorFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoordinadorFacade() {
        super(Coordinador.class);
    }

    @Override
    public List<Coordinador> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT PRIN_PERSONA.PER_CODIGO, PROFESOR.APELLIDO_PROFESOR+' '+ PROFESOR.NOMBRE_PROFESOR NOMBRE_COORDINADOR ");
        sb.append("FROM PROFESOR ");
        sb.append("INNER JOIN PRIN_PERSONA ON PROFESOR.CED_PAS_PROFESOR = PRIN_PERSONA.PER_ID_DOC ");
        sb.append("ORDER BY NOMBRE_COORDINADOR");
        Query q = em.createNativeQuery(sb.toString(), Coordinador.class);
        return q.getResultList();
    }

//    @Override
//    public List<Coordinador> findByArea(Short areaCodigo) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT DISTINCT PRIN_PERSONA.PER_CODIGO, PROGRAMA.COORDINADOR NOMBRE_COORDINADOR ");
//        sb.append("FROM PROGRAMA INNER JOIN ");
//        sb.append("PRIN_PERSONA ON PROGRAMA.CED_COORDINADOR = PRIN_PERSONA.PER_ID_DOC ");
//        sb.append("WHERE (PROGRAMA.ARE_CODIGO = ?) order by PROGRAMA.COORDINADOR");
//        Query q = em.createNativeQuery(sb.toString(), Coordinador.class);
//        q.setParameter(1, areaCodigo);
//        return q.getResultList();
//    }
}
