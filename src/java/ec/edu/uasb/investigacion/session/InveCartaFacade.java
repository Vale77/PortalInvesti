/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import java.util.ArrayList;
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
public class InveCartaFacade extends AbstractFacade<InveCarta> implements InveCartaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveCartaFacade() {
        super(InveCarta.class);
    }

    @Override
    public List<InveCarta> findBy(Short anio, Short area) {
        Query q = em.createQuery("SELECT i FROM InveCarta i WHERE i.inveProyecto.pryAnioAcad = :anio and i.inveProyecto.pryAreaAcad = :area ");
        q.setParameter("anio", anio).setParameter("area", area);
        return q.getResultList();

    }

    @Override
    public List<InveCarta> findBy(Short anio) {
        List<InveCarta> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT INVE_CARTA.*,AREA.*,")
//                .append(" isnull(INVE_LECTOR.LEC_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_LECTOR.LEC_CODIGO ASC))*-1)  LEC_CODIGO,INVE_LECTOR.*, ")
//                .append(" isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1)  SEG_CODIGO,INVE_SEGUIMIENTO.* ")
//                .append(" FROM INVE_PROYECTO  ")
//                .append(" INNER JOIN AREA   ON (INVE_PROYECTO.PRY_AREA_ACAD = AREA.ARE_CODIGO) ")
//                .append(" INNER JOIN INVE_CARTA ON (INVE_CARTA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND INVE_CARTA.CAR_ES_PERTINENTE = 'S') ")
//                .append(" LEFT OUTER JOIN INVE_LECTOR ON (INVE_PROYECTO.PRY_CODIGO = INVE_LECTOR.PRY_CODIGO ")
//                .append(" AND INVE_LECTOR.ROL_CODIGO = 1 AND isnull(INVE_LECTOR.LEC_ACEPTACION,'S') <> 'N') ")
//                .append(" LEFT OUTER JOIN INVE_SEGUIMIENTO ON (INVE_PROYECTO.PRY_CODIGO = INVE_SEGUIMIENTO.PRY_CODIGO ")
//                .append(" AND INVE_SEGUIMIENTO.TSE_CODIGO = 1 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A' ) ")
//                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ?");
        sql.append(" SELECT INVE_CARTA.*,AREA.*,")
                .append(" isnull(ULTIMA_LECTURA.LEC_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMA_LECTURA.LEC_CODIGO ASC))*-1)  LEC_CODIGO,ULTIMA_LECTURA.*, ")
                .append("  isnull(ULTIMO_SEG_LECTURA.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMO_SEG_LECTURA.SEG_CODIGO ASC))*-1)  SEG_CODIGO,ULTIMO_SEG_LECTURA.* ")
                .append(" FROM INVE_PROYECTO  ")
                .append(" INNER JOIN AREA   ON (INVE_PROYECTO.PRY_AREA_ACAD = AREA.ARE_CODIGO) ")
                .append(" INNER JOIN INVE_CARTA ON (INVE_CARTA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND INVE_CARTA.CAR_ES_PERTINENTE = 'S') ")
                .append(" LEFT OUTER JOIN (SELECT  INVE_LECTOR.*,ROW_NUMBER ()OVER (PARTITION BY INVE_LECTOR.PRY_CODIGO ORDER BY INVE_LECTOR.LEC_NUMERO desc) NUM_FILA  ")
                .append("       FROM INVE_LECTOR WHERE ROL_CODIGO = 1 AND isnull(INVE_LECTOR.LEC_ACEPTACION,'S') <> 'N') ULTIMA_LECTURA ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMA_LECTURA.PRY_CODIGO AND  ULTIMA_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT INVE_SEGUIMIENTO.*,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("         FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 1 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A') ULTIMO_SEG_LECTURA ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMO_SEG_LECTURA.PRY_CODIGO  AND  ULTIMO_SEG_LECTURA.NUM_FILA = 1) ")
                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ?");
        Query q = em.createNativeQuery(sql.toString(), "cartasResults");
        q.setParameter(1, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveCarta carta = (InveCarta) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            InveLector lector = (InveLector) reg[2];
            InveSeguimiento seguim = (InveSeguimiento) reg[3];
            carta.setArea(area);
            if (lector.getLecCodigo() != null) {
                carta.setLector(lector);
            }
            if (seguim.getSegCodigo() != null) {
                carta.setSeguimiento(seguim);
            }
            lista.add(carta);
        }
        return lista;
    }
    
    @Override
    public List<InveCarta> findLectorBy(InveConvocatoria convoc) {
        List<InveCarta> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT INVE_CARTA.*,AREA.*,")
                .append(" isnull(ULTIMA_LECTURA.LEC_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMA_LECTURA.LEC_CODIGO ASC))*-1)  LEC_CODIGO,ULTIMA_LECTURA.*, ")
                .append("  isnull(ULTIMO_SEG_LECTURA.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMO_SEG_LECTURA.SEG_CODIGO ASC))*-1)  SEG_CODIGO,ULTIMO_SEG_LECTURA.* ")
                .append(" FROM INVE_PROYECTO  ")
                .append(" INNER JOIN AREA   ON (INVE_PROYECTO.PRY_AREA_ACAD = AREA.ARE_CODIGO) ")
                .append(" INNER JOIN INVE_CARTA ON (INVE_CARTA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND INVE_CARTA.CAR_ES_PERTINENTE = 'S') ")
                .append(" LEFT OUTER JOIN (SELECT  INVE_LECTOR.*,ROW_NUMBER ()OVER (PARTITION BY INVE_LECTOR.PRY_CODIGO ORDER BY INVE_LECTOR.LEC_NUMERO desc) NUM_FILA  ")
                .append("       FROM INVE_LECTOR WHERE ROL_CODIGO = 1 AND isnull(INVE_LECTOR.LEC_ACEPTACION,'S') <> 'N') ULTIMA_LECTURA ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMA_LECTURA.PRY_CODIGO AND  ULTIMA_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT INVE_SEGUIMIENTO.*,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("         FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 1 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A') ULTIMO_SEG_LECTURA ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMO_SEG_LECTURA.PRY_CODIGO  AND  ULTIMO_SEG_LECTURA.NUM_FILA = 1) ")
                .append(" WHERE INVE_PROYECTO.CVO_CODIGO = ?");
        Query q = em.createNativeQuery(sql.toString(), "cartasResults");
        q.setParameter(1, convoc.getCvoCodigo());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveCarta carta = (InveCarta) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            InveLector lector = (InveLector) reg[2];
            InveSeguimiento seguim = (InveSeguimiento) reg[3];
            carta.setArea(area);
            if (lector.getLecCodigo() != null) {
                carta.setLector(lector);
            }
            if (seguim.getSegCodigo() != null) {
                carta.setSeguimiento(seguim);
            }
            lista.add(carta);
        }
        return lista;
    }
}
