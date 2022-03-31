/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.Titulo;
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
public class TituloFacade extends AbstractFacade<Titulo> implements TituloFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TituloFacade() {
        super(Titulo.class);
    }

    @Override
    public Titulo find(Object id, String categoria) {
        StringBuilder sb = new StringBuilder();

        Titulo tit = null;
        Query q = null;
        switch (categoria) {
            case "Docentes":
                sb.append("SELECT PRIN_PERSONA.PER_CODIGO,VTD.TITULO, VTD.UNIVERSIDAD_OBTENCION, VTD.PAIS_OBTENCION, VTD.REGISTRO_SENECYT")
                        .append(" FROM (interfaseOcu.dbo.PROFESOR WITH (NOLOCK)")
                        .append(" LEFT OUTER JOIN interfaseOcu.GESTIONDOCENTE.V_TITULOS_DOCENTE VTD  WITH (NOLOCK)ON (PROFESOR.CODIGO_PROFESOR = VTD.PRS_CODNUM))")
                        .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA WITH (NOLOCK) ON (PRIN_PERSONA.PER_ID_DOC = PROFESOR.CED_PAS_PROFESOR)")
                        .append(" WHERE  PRIN_PERSONA.PER_CODIGO = ?");
                break;
            case "Graduados Mae":
                sb.append("SELECT  TOP(1) PER_CODIGO,  INFO_GRADUADOS.IGR_NOMBRE_TITULO  TITULO, 'Universidad Andina Simón Bolívar' UNIVERSIDAD_OBTENCION, 'ECUADOR' PAIS_OBTENCION ")
                        .append(" FROM GESTIONACADEMICA.INFO_GRADUADOS   WITH (NOLOCK)")
                        .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA WITH (NOLOCK)  ON (INFO_GRADUADOS.IGR_CED_PAS = PRIN_PERSONA.PER_ID_DOC) ")
                        .append(" WHERE INFO_GRADUADOS.IGR_COD_NIVACAD = 2 AND  PRIN_PERSONA.PER_CODIGO = ? ")
                        .append(" ORDER BY INFO_GRADUADOS.IGR_COD_NIVACAD DESC ");
                break;
            case "Graduados Doc":
                sb.append("SELECT  TOP(1) PER_CODIGO,  INFO_GRADUADOS.IGR_NOMBRE_TITULO  TITULO, 'Universidad Andina Simón Bolívar' UNIVERSIDAD_OBTENCION, 'ECUADOR' PAIS_OBTENCION ")
                        .append(" FROM GESTIONACADEMICA.INFO_GRADUADOS   WITH (NOLOCK)")
                        .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA WITH (NOLOCK)  ON (INFO_GRADUADOS.IGR_CED_PAS = PRIN_PERSONA.PER_ID_DOC) ")
                        .append(" WHERE INFO_GRADUADOS.IGR_COD_NIVACAD = 3 AND  PRIN_PERSONA.PER_CODIGO = ? ")
                        .append(" ORDER BY INFO_GRADUADOS.IGR_COD_NIVACAD DESC ");
                break;
            case "Estudiantes":
                sb.append("SELECT  TOP(1) PER_CODIGO,  INFO_ESTUDIANTES.IES_TITULO_OBTENER  TITULO, 'Universidad Andina Simón Bolívar' UNIVERSIDAD_OBTENCION, 'ECUADOR' PAIS_OBTENCION ")
                        .append(" FROM GESTIONACADEMICA.INFO_ESTUDIANTES   WITH (NOLOCK)")
                        .append(" INNER JOIN interfaseOcu.dbo.PRIN_PERSONA WITH (NOLOCK)  ON (INFO_ESTUDIANTES.IES_CED_PAS = PRIN_PERSONA.PER_ID_DOC) ")
                        .append(" WHERE INFO_ESTUDIANTES.IES_COD_NIVACAD in (2,3) AND  PRIN_PERSONA.PER_CODIGO = ? ")
                        .append(" ORDER BY INFO_ESTUDIANTES.IES_COD_NIVACAD DESC ");
                break;
            default:
                break;
        }
        q = em.createNativeQuery(sb.toString(), Titulo.class).setParameter(1, id);
        try {
            tit = (Titulo) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return tit;
    }

}
