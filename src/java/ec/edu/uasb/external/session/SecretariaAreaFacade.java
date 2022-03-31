/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.SecretariaArea;
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
public class SecretariaAreaFacade extends AbstractFacade<SecretariaArea> implements SecretariaAreaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SecretariaAreaFacade() {
        super(SecretariaArea.class);
    }

    @Override
    public List<SecretariaArea> findAllByTipo(String tipo) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT PRIN_PERSONA.PER_CODIGO, ")
                .append("PRIN_PERSONA.PER_PRIMER_APELLIDO + ' ' + PRIN_PERSONA.PER_SEGUNDO_APELLIDO + ' ' + PRIN_PERSONA.PER_NOMBRES AS NOMBRE_SECRETARIA ")
                .append("FROM V_SECRETARIA ")
                .append("INNER JOIN  PRIN_PERSONA ON V_SECRETARIA.CED_PAS_USUARIO = PRIN_PERSONA.PER_ID_DOC ");
        if (tipo.equals("conAsistentes")) {
            sb.append("UNION ")
                    .append("SELECT DISTINCT PRIN_PERSONA.PER_CODIGO, ")
                    .append("PRIN_PERSONA.PER_PRIMER_APELLIDO + ' ' + PRIN_PERSONA.PER_SEGUNDO_APELLIDO + ' ' + PRIN_PERSONA.PER_NOMBRES AS NOMBRE_SECRETARIA ")
                    .append("FROM ASISTENTE_ACADEMICO ")
                    .append("INNER JOIN PRIN_PERSONA ON ASISTENTE_ACADEMICO.CED_PAS_PROFESOR = PRIN_PERSONA.PER_ID_DOC  ");
        }
        sb.append(" ORDER BY NOMBRE_SECRETARIA");
        Query q = em.createNativeQuery(sb.toString(), SecretariaArea.class);
        return q.getResultList();
    }

//    @Override
//    public List<SecretariaArea> findByArea(Short areaCodigo) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT DISTINCT PRIN_PERSONA.PER_CODIGO, ");
//        sb.append("PRIN_PERSONA.PER_PRIMER_APELLIDO + ' ' + PRIN_PERSONA.PER_SEGUNDO_APELLIDO + ' ' + PRIN_PERSONA.PER_NOMBRES AS NOMBRE_SECRETARIA ");
//        sb.append("FROM V_SECRETARIA INNER JOIN ");
//        sb.append("PRIN_PERSONA ON V_SECRETARIA.CED_PAS_USUARIO = PRIN_PERSONA.PER_ID_DOC ");
//        sb.append("WHERE (V_SECRETARIA.ARE_CODIGO = ?) order by NOMBRE_SECRETARIA");
//        Query q = em.createNativeQuery(sb.toString(), SecretariaArea.class);
//        q.setParameter(1, areaCodigo);
//        return q.getResultList();
//    }
    @Override
    public SecretariaArea find(Object id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT PRIN_PERSONA.PER_CODIGO, ");
        sb.append("PRIN_PERSONA.PER_PRIMER_APELLIDO + ' ' + PRIN_PERSONA.PER_SEGUNDO_APELLIDO + ' ' + PRIN_PERSONA.PER_NOMBRES AS NOMBRE_SECRETARIA ");
        sb.append("FROM PRIN_PERSONA WHERE  PRIN_PERSONA.PER_CODIGO = ?");
        Query q = em.createNativeQuery(sb.toString(), SecretariaArea.class);
        q.setParameter(1, id);
        return (SecretariaArea) q.getSingleResult();
    }

}
