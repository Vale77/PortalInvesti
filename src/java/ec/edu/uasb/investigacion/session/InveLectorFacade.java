/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.principal.entities.PrinPersona;
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
public class InveLectorFacade extends AbstractFacade<InveLector> implements InveLectorFacadeLocal {
    
    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public InveLectorFacade() {
        super(InveLector.class);
    }

    // lectores para un proyecto
    @Override
    public List<InveLector> findByRol(InveProyecto inveProyecto, Short rolLec) {
        List<InveLector> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT INVE_LECTOR.*,isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1)  SEG_CODIGO, INVE_SEGUIMIENTO.* ")
                .append(" FROM INVE_LECTOR ")
                .append(" LEFT OUTER JOIN INVE_SEGUIMIENTO ON (INVE_LECTOR.PRY_CODIGO = INVE_SEGUIMIENTO.PRY_CODIGO ")
                .append("       AND INVE_LECTOR.LEC_CODIGO = INVE_SEGUIMIENTO.LEC_CODIGO AND INVE_SEGUIMIENTO.TSE_CODIGO = 0) ")
                .append(" WHERE (INVE_LECTOR.PRY_CODIGO = ?) AND (INVE_LECTOR.ROL_CODIGO = 2) ");
//        Query q = em.createNativeQuery(sb.toString(), "SegLecturasFinales");
        Query q = em.createNativeQuery(sb.toString(), "SegLecturasResults");
        q.setParameter(1, inveProyecto.getPryCodigo()).setParameter(2, rolLec);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveLector lector = (InveLector) reg[0];
            lector.setSeguimiento((InveSeguimiento) reg[1]);
            lista.add(lector);
        }
        return lista;
    }

    /*  lista de lectores que han aceptado la lectura y registran seguimiento*/
    @Override
    public List<InveLector> findBy(Short anio) {
        List<InveLector> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ULTIMA_LECTURA.*,isnull(ULTIMO_SEG_VALORA.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMO_SEG_VALORA.SEG_CODIGO ASC))*-1)  SEG_CODIGO,  ")
                .append(" ULTIMO_SEG_VALORA.*, INVE_VALORACION.ESV_CODIGO ESTADO, ")
                .append(" convert(varchar,INVE_VALORACION.VAL_FECHA_RECEP,103)+'-->'+INVE_ESTADO_VALORACION.ESV_ESTADO+': '+INVE_ESTADO_VALORACION.ESV_DESCRIPCION TEXTO_ESTADO  ")
                .append(" FROM (select  INVE_LECTOR.*,ROW_NUMBER ()OVER (partition by INVE_LECTOR.PRY_CODIGO order by INVE_LECTOR.LEC_NUMERO desc) NUM_FILA ")
                .append("       from INVE_LECTOR where ROL_CODIGO = 1 AND isnull(INVE_LECTOR.LEC_ACEPTACION,'S') <> 'N') ULTIMA_LECTURA  ")
                .append("     INNER JOIN INVE_PROYECTO  ON (ULTIMA_LECTURA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND ULTIMA_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN INVE_VALORACION  on ULTIMA_LECTURA.LEC_CODIGO = INVE_VALORACION.LEC_CODIGO   ")
                .append(" LEFT OUTER JOIN INVE_ESTADO_VALORACION  ON (INVE_VALORACION.ESV_CODIGO = INVE_ESTADO_VALORACION.ESV_CODIGO) ")
                .append(" LEFT OUTER JOIN (SELECT INVE_SEGUIMIENTO.*,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("  FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 2 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A') ULTIMO_SEG_VALORA  ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMO_SEG_VALORA.PRY_CODIGO AND ULTIMO_SEG_VALORA.NUM_FILA = 1)  ")
                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ? ");
        Query q = em.createNativeQuery(sb.toString(), "SegLecturasResults");
        q.setParameter(1, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveLector lector = (InveLector) reg[0];
            lector.setSeguimiento((InveSeguimiento) reg[1]);
            lector.setEstadoVal((String) reg[2]);
            lector.setTextoEstado((String) reg[3]);
            lista.add(lector);
        }
        return lista;
    }
    
    @Override
    public List<InveLector> findValoracionBy(InveConvocatoria convoc) {
        List<InveLector> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ULTIMA_LECTURA.*,isnull(ULTIMO_SEG_VALORA.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMO_SEG_VALORA.SEG_CODIGO ASC))*-1)  SEG_CODIGO,  ")
                .append(" ULTIMO_SEG_VALORA.*, INVE_VALORACION.ESV_CODIGO ESTADO, ")
                .append(" convert(varchar,INVE_VALORACION.VAL_FECHA_RECEP,103)+'-->'+INVE_ESTADO_VALORACION.ESV_ESTADO+': '+INVE_ESTADO_VALORACION.ESV_DESCRIPCION TEXTO_ESTADO  ")
                .append(" FROM (select  INVE_LECTOR.*,ROW_NUMBER ()OVER (partition by INVE_LECTOR.PRY_CODIGO order by INVE_LECTOR.LEC_NUMERO desc) NUM_FILA ")
                .append("       from INVE_LECTOR where ROL_CODIGO = 1 AND isnull(INVE_LECTOR.LEC_ACEPTACION,'S') <> 'N') ULTIMA_LECTURA  ")
                .append("     INNER JOIN INVE_PROYECTO  ON (ULTIMA_LECTURA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND ULTIMA_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN INVE_VALORACION  on ULTIMA_LECTURA.LEC_CODIGO = INVE_VALORACION.LEC_CODIGO   ")
                .append(" LEFT OUTER JOIN INVE_ESTADO_VALORACION  ON (INVE_VALORACION.ESV_CODIGO = INVE_ESTADO_VALORACION.ESV_CODIGO) ")
                .append(" LEFT OUTER JOIN (SELECT INVE_SEGUIMIENTO.*,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA ")
                .append("  FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 2 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A') ULTIMO_SEG_VALORA  ")
                .append("  ON (INVE_PROYECTO.PRY_CODIGO = ULTIMO_SEG_VALORA.PRY_CODIGO AND ULTIMO_SEG_VALORA.NUM_FILA = 1)  ")
                .append(" WHERE INVE_PROYECTO.CVO_CODIGO = ? ");
        Query q = em.createNativeQuery(sb.toString(), "SegLecturasResults");
        q.setParameter(1, convoc.getCvoCodigo());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveLector lector = (InveLector) reg[0];
            lector.setSeguimiento((InveSeguimiento) reg[1]);
            lector.setEstadoVal((String) reg[2]);
            lector.setTextoEstado((String) reg[3]);
            lista.add(lector);
        }
        return lista;
    }
    //        sb.append("SELECT INVE_LECTOR.*,isnull(ULTIMO_SEG_VALORA.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY ULTIMO_SEG_VALORA.SEG_CODIGO ASC))*-1)  SEG_CODIGO, ULTIMO_SEG_VALORA.*, ")
//                .append(" ULTIMA_VALORACION.ESTADO,ULTIMA_VALORACION.TEXTO_ESTADO ")
//                .append(" FROM (select ROW_NUMBER ()OVER (PARTITION BY L.PRY_CODIGO,L.PER_CODIGO ORDER BY L.LEC_NUMERO desc) ULTIMA_LEC ,* from INVE_LECTOR L ")
//                .append(" where  L.ROL_CODIGO = 2 AND  L.LEC_ACEPTACION = 'S' AND L.LEC_FECHA_DESIGNACION IS NOT NULL ) INVE_LECTOR ")
//                .append(" LEFT OUTER JOIN (SELECT INVE_SEGUIMIENTO.*,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC desc) NUM_FILA  ")
//                .append(" FROM INVE_SEGUIMIENTO  WHERE INVE_SEGUIMIENTO.TSE_CODIGO = 0 AND INVE_SEGUIMIENTO.SEG_ESTADO_REG ='A') ULTIMO_SEG_VALORA ")
//                .append(" ON (INVE_LECTOR.PRY_CODIGO = ULTIMO_SEG_VALORA.PRY_CODIGO AND INVE_LECTOR.LEC_CODIGO = ULTIMO_SEG_VALORA.LEC_CODIGO  AND NUM_FILA = 1) ")
//                .append(" LEFT OUTER JOIN (SELECT INVE_LECTOR.PRY_CODIGO,INVE_VALORACION.LEC_CODIGO,INVE_ESTADO_VALORACION.ESV_CODIGO ESTADO, ")
//                .append("         convert(varchar,INVE_VALORACION.VAL_FECHA_RECEP,103)+'-->'+INVE_ESTADO_VALORACION.ESV_ESTADO+': '+INVE_ESTADO_VALORACION.ESV_DESCRIPCION TEXTO_ESTADO, ")
//                .append("         ROW_NUMBER ()OVER (PARTITION BY INVE_LECTOR.PRY_CODIGO,INVE_VALORACION.LEC_CODIGO ORDER BY INVE_VALORACION.VAL_FECHA_RECEP desc) NUM_FILA ")
//                .append("       FROM (INVE_VALORACION  ")
//                .append("       INNER JOIN INVE_ESTADO_VALORACION  ON (INVE_VALORACION.ESV_CODIGO = INVE_ESTADO_VALORACION.ESV_CODIGO)) ")
//                .append(" INNER JOIN INVE_LECTOR  ON INVE_VALORACION.LEC_CODIGO = INVE_LECTOR.LEC_CODIGO AND INVE_LECTOR.ROL_CODIGO = 2) ULTIMA_VALORACION ")
//                .append(" ON INVE_LECTOR.PRY_CODIGO = ULTIMA_VALORACION.PRY_CODIGO AND ULTIMA_VALORACION.LEC_CODIGO = INVE_LECTOR.LEC_CODIGO AND ULTIMA_VALORACION.NUM_FILA = 1 ")
//                .append("  WHERE INVE_LECTOR.PRY_CODIGO = ? AND INVE_LECTOR.ULTIMA_LEC = 1 ");
//        Query q = em.createNativeQuery(sb.toString(), "SegLecturasResults");
    // Todas las lecturas para informe final (Rol = 2) con aceptacion, además del ultimo seguimiento a estas lecturas TSE_CODIGO = 3
    @Override
    public List<InveLector> findValFinalByProyecto(InveProyecto inveProyecto) {
        List<InveLector> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT ULTIMA_LECTURA_INFO_FINAL.*,INVE_VALORACION.ESV_CODIGO ESTADO, ")
                .append(" convert(varchar,INVE_VALORACION.VAL_FECHA_RECEP,103)+'-->'+INVE_ESTADO_VALORACION.ESV_ESTADO+': '+INVE_ESTADO_VALORACION.ESV_DESCRIPCION TEXTO_ESTADO ")
                .append(" FROM (select  INVE_LECTOR.*,ROW_NUMBER ()OVER (partition by INVE_LECTOR.PRY_CODIGO,INVE_LECTOR.PER_CODIGO order by INVE_LECTOR.LEC_NUMERO desc) NUM_FILA ")
                .append("       from INVE_LECTOR where ROL_CODIGO = 2 AND  LEC_ACEPTACION = 'S'   ) ULTIMA_LECTURA_INFO_FINAL ")
                .append(" LEFT OUTER JOIN INVE_VALORACION  on ULTIMA_LECTURA_INFO_FINAL.LEC_CODIGO = INVE_VALORACION.LEC_CODIGO ")
                .append(" LEFT OUTER JOIN INVE_ESTADO_VALORACION  ON (INVE_VALORACION.ESV_CODIGO = INVE_ESTADO_VALORACION.ESV_CODIGO) ")
                .append(" WHERE ULTIMA_LECTURA_INFO_FINAL.PRY_CODIGO = ? and ULTIMA_LECTURA_INFO_FINAL.NUM_FILA = 1  AND LEC_FECHA_DESIGNACION IS NOT NULL ");
        Query q = em.createNativeQuery(sb.toString(), "UltimasLecturasValInfoFinal");
        q.setParameter(1, inveProyecto.getPryCodigo());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveLector lector = (InveLector) reg[0];
            lector.setEstadoVal((String) reg[1]);
            lector.setTextoEstado((String) reg[2]);
            lista.add(lector);
        }
        return lista;
    }
    
    @Override
    public List<PrinPersona> findAllDocentes() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT PRIN_PERSONA.* FROM PROFESOR")
                .append(" INNER JOIN PRIN_PERSONA ON PROFESOR.CED_PAS_PROFESOR = PRIN_PERSONA.PER_ID_DOC ")
                .append(" UNION ")
                .append(" SELECT PRIN_PERSONA.* FROM INVE_LECTOR_CONTRATADO ")
                .append(" INNER JOIN PRIN_PERSONA ON INVE_LECTOR_CONTRATADO.PER_CODIGO = PRIN_PERSONA.PER_CODIGO ");
        
        Query q = em.createNativeQuery(sb.toString(), PrinPersona.class);
        return q.getResultList();
    }
    
    @Override
    public List<PrinPersona> findAllInvestigadores() {
        Query q = em.createQuery("SELECT DISTINCT p.prinPersona FROM InveProyecto p order by p.prinPersona.perPrimerApellido,p.prinPersona.perSegundoApellido,p.prinPersona.perNombres");
        return q.getResultList();
    }
    
    @Override
    public List<PrinPersona> findAllLectores() {
        Query q = em.createQuery("SELECT DISTINCT l.prinPersona FROM InveLector l order by l.prinPersona.perPrimerApellido,l.prinPersona.perSegundoApellido,l.prinPersona.perNombres");
        return q.getResultList();
    }
    
    @Override
    public void edit(InveLector entity, InveSeguimiento seguimiento) {
        if (entity.getLecCodigo() != null && entity.getLecNumero() > 1) {
            em.merge(entity);
            em.flush();
            em.persist(seguimiento);
        } else if (entity.getLecCodigo() == null) {
            entity.setLecNumero(new Short("1"));
            em.persist(entity);
            em.flush();
            if (seguimiento.getInveTipoSeguimiento().getTseCodigo() == 0) { // tipo de seguimiento lectura de informe final
                seguimiento.setLecCodigo(entity.getLecCodigo());
            }
            em.persist(seguimiento);
        } else {
            em.merge(entity);
            if (entity.getLecAceptacion().equals("N")) {
                seguimiento.setSegEstadoReg("I");
                em.merge(seguimiento);
            }
        }
//        enviarNotificacion(entity);
    }
    
}
