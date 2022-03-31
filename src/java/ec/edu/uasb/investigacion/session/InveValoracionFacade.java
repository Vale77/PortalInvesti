/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.principal.session.PrinDocumentoFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveValoracionFacade extends AbstractFacade<InveValoracion> implements InveValoracionFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @EJB
    private PrinDocumentoFacadeLocal prinDocumentoFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveValoracionFacade() {
        super(InveValoracion.class);
    }

    @Override
    public List<InveValoracion> getValoracionBy(InveEstadoValoracion iev) {
        Query q = em.createQuery("SELECT i FROM InveValoracion i WHERE i.inveEstadoValoracion = :iev");
        q.setParameter("iev", iev);
        return q.getResultList();
    }

    @Override
    public List<InveValoracion> getValoracionesBy(Short anio) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<InveValoracion> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // lecturas (1) con valoraciones de proyecto y (2) positivas
        sb.append("SELECT  INVE_VALORACION.*,INVE_LECTOR.*,ULT_SEGUIM_LECTURA.*, ")
                .append(" isnull(INVE_INFORME_OPER.PRY_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_INFORME_OPER.PRY_CODIGO ASC))*-1)  PRY_CODIGO,INVE_INFORME_OPER.*, ")
                .append(" (case ")
                .append(" when PRORROGA1.SEG_FECHA_LIMITE is null then DATEDIFF(day,ULT_SEGUIM_LECTURA.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" when PRORROGA2.SEG_FECHA_LIMITE  is null then DATEDIFF(day,PRORROGA1.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" when PRORROGAEXT.SEG_FECHA_LIMITE  is null then DATEDIFF(day,PRORROGA2.SEG_FECHA_LIMITE,GETDATE()) ")
                .append("  else DATEDIFF(day,PRORROGAEXT.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" end) DIAS,")
                .append(" PRORROGA1.SEG_FECHA_LIMITE PRO1, ")
                .append(" PRORROGA2.SEG_FECHA_LIMITE PRO2, ")
                .append(" PRORROGAEXT.SEG_FECHA_LIMITE PROEXT,")
                .append(" PRORROGA1.SEG_TIPO_COMUNIC+': '+ PRORROGA1.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGA1.SEG_FECHA_COMUNIC,103) COMUNIC1,")
                .append(" PRORROGA2.SEG_TIPO_COMUNIC+': '+ PRORROGA2.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGA2.SEG_FECHA_COMUNIC,103) COMUNIC2,")
                .append(" PRORROGAEXT.SEG_TIPO_COMUNIC+': '+ PRORROGAEXT.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGAEXT.SEG_FECHA_COMUNIC,103) COMUNIC3 ")
                .append(" FROM INVE_LECTOR ")
                .append(" INNER JOIN INVE_PROYECTO ON (INVE_LECTOR.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO) ")
                .append(" INNER JOIN INVE_VALORACION ON (INVE_VALORACION.LEC_CODIGO = INVE_LECTOR.LEC_CODIGO) ")
                .append(" INNER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC DESC) NUM_FILA ")
                .append("           FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 2 AND SEG_ESTADO_REG = 'A') ULT_SEGUIM_LECTURA ")
                .append("      ON (ULT_SEGUIM_LECTURA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO  AND  ULT_SEGUIM_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA ")
                .append("           FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 4 AND SEG_ESTADO_REG = 'A') PRORROGA1 ")
                .append("   ON (PRORROGA1.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGA1.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA  ")
                .append("      FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 4 AND SEG_ESTADO_REG = 'A') PRORROGA2 ")
                .append("   ON (PRORROGA2.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGA2.NUM_FILA = 2) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA ")
                .append("      FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 6 AND SEG_ESTADO_REG = 'A') PRORROGAEXT ")
                .append("      ON (PRORROGAEXT.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGAEXT.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN INVE_INFORME_OPER ON (INVE_INFORME_OPER.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO)")
                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ? AND INVE_VALORACION.ESV_CODIGO IN ('a', 'b') AND INVE_LECTOR.ROL_CODIGO = 1  ")
                .append(" ORDER BY INVE_PROYECTO.PRY_CODIGO_CI ");
        Query q = em.createNativeQuery(sb.toString(), "ValoracionesResults");
        q.setParameter(1, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            try {
                Object[] reg = (Object[]) record;
                InveValoracion valorac = (InveValoracion) reg[0];
                valorac.setInveLector((InveLector) reg[1]);
                valorac.setInveSeguimiento((InveSeguimiento) reg[2]);
                valorac.setInveInformeOper((InveInformeOper) reg[3]);
                valorac.setDias((Integer) reg[4]);
                valorac.setPro1(reg[5] == null ? null : dateFormat.parse(reg[5].toString()));
                valorac.setPro2(reg[6] == null ? null : dateFormat.parse(reg[6].toString()));
                valorac.setProExt(reg[7] == null ? null : dateFormat.parse(reg[7].toString()));
                valorac.setComunic1(reg[8] == null ? null : reg[8].toString());
                valorac.setComunic2(reg[9] == null ? null : reg[9].toString());
                valorac.setComunic3(reg[10] == null ? null : reg[10].toString());
                lista.add(valorac);
            } catch (ParseException ex) {
                Logger.getLogger(InveValoracionFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
 @Override
    public List<InveValoracion> getValorConvocatoriaBy(InveConvocatoria convoc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<InveValoracion> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // lecturas (1) con valoraciones de proyecto y (2) positivas
        sb.append("SELECT  INVE_VALORACION.*,INVE_LECTOR.*,ULT_SEGUIM_LECTURA.*, ")
                .append(" isnull(INVE_INFORME_OPER.PRY_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_INFORME_OPER.PRY_CODIGO ASC))*-1)  PRY_CODIGO,INVE_INFORME_OPER.*, ")
                .append(" (case ")
                .append(" when PRORROGA1.SEG_FECHA_LIMITE is null then DATEDIFF(day,ULT_SEGUIM_LECTURA.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" when PRORROGA2.SEG_FECHA_LIMITE  is null then DATEDIFF(day,PRORROGA1.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" when PRORROGAEXT.SEG_FECHA_LIMITE  is null then DATEDIFF(day,PRORROGA2.SEG_FECHA_LIMITE,GETDATE()) ")
                .append("  else DATEDIFF(day,PRORROGAEXT.SEG_FECHA_LIMITE,GETDATE()) ")
                .append(" end) DIAS,")
                .append(" PRORROGA1.SEG_FECHA_LIMITE PRO1, ")
                .append(" PRORROGA2.SEG_FECHA_LIMITE PRO2, ")
                .append(" PRORROGAEXT.SEG_FECHA_LIMITE PROEXT,")
                .append(" PRORROGA1.SEG_TIPO_COMUNIC+': '+ PRORROGA1.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGA1.SEG_FECHA_COMUNIC,103) COMUNIC1,")
                .append(" PRORROGA2.SEG_TIPO_COMUNIC+': '+ PRORROGA2.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGA2.SEG_FECHA_COMUNIC,103) COMUNIC2,")
                .append(" PRORROGAEXT.SEG_TIPO_COMUNIC+': '+ PRORROGAEXT.SEG_NRO_COMUNIC+', '+ convert(varchar,PRORROGAEXT.SEG_FECHA_COMUNIC,103) COMUNIC3 ")
                .append(" FROM INVE_LECTOR ")
                .append(" INNER JOIN INVE_PROYECTO ON (INVE_LECTOR.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO) ")
                .append(" INNER JOIN INVE_VALORACION ON (INVE_VALORACION.LEC_CODIGO = INVE_LECTOR.LEC_CODIGO) ")
                .append(" INNER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC DESC) NUM_FILA ")
                .append("           FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 2 AND SEG_ESTADO_REG = 'A') ULT_SEGUIM_LECTURA ")
                .append("      ON (ULT_SEGUIM_LECTURA.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO  AND  ULT_SEGUIM_LECTURA.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA ")
                .append("           FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 4 AND SEG_ESTADO_REG = 'A') PRORROGA1 ")
                .append("   ON (PRORROGA1.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGA1.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA  ")
                .append("      FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 4 AND SEG_ESTADO_REG = 'A') PRORROGA2 ")
                .append("   ON (PRORROGA2.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGA2.NUM_FILA = 2) ")
                .append(" LEFT OUTER JOIN (SELECT *,ROW_NUMBER ()OVER (PARTITION BY INVE_SEGUIMIENTO.PRY_CODIGO ORDER BY INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC ASC) NUM_FILA ")
                .append("      FROM INVE_SEGUIMIENTO   WHERE TSE_CODIGO = 6 AND SEG_ESTADO_REG = 'A') PRORROGAEXT ")
                .append("      ON (PRORROGAEXT.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND  PRORROGAEXT.NUM_FILA = 1) ")
                .append(" LEFT OUTER JOIN INVE_INFORME_OPER ON (INVE_INFORME_OPER.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO)")
                .append(" WHERE INVE_PROYECTO.CVO_CODIGO = ? AND INVE_VALORACION.ESV_CODIGO IN ('a', 'b') AND INVE_LECTOR.ROL_CODIGO = 1  ")
                .append(" ORDER BY INVE_PROYECTO.PRY_CODIGO_CI ");
        Query q = em.createNativeQuery(sb.toString(), "ValoracionesResults");
        q.setParameter(1, convoc.getCvoCodigo());
        List temp = q.getResultList();
        for (Object record : temp) {
            try {
                Object[] reg = (Object[]) record;
                InveValoracion valorac = (InveValoracion) reg[0];
                valorac.setInveLector((InveLector) reg[1]);
                valorac.setInveSeguimiento((InveSeguimiento) reg[2]);
                valorac.setInveInformeOper((InveInformeOper) reg[3]);
                valorac.setDias((Integer) reg[4]);
                valorac.setPro1(reg[5] == null ? null : dateFormat.parse(reg[5].toString()));
                valorac.setPro2(reg[6] == null ? null : dateFormat.parse(reg[6].toString()));
                valorac.setProExt(reg[7] == null ? null : dateFormat.parse(reg[7].toString()));
                valorac.setComunic1(reg[8] == null ? null : reg[8].toString());
                valorac.setComunic2(reg[9] == null ? null : reg[9].toString());
                valorac.setComunic3(reg[10] == null ? null : reg[10].toString());
                lista.add(valorac);
            } catch (ParseException ex) {
                Logger.getLogger(InveValoracionFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    @Override
    public void edit(InveValoracion entity, PrinDocumento doc, InveSeguimiento seguimiento) {
        if (entity.getValCodigo() == null) {
            super.create(entity);
            em.flush();
        } else {
            super.edit(entity);
        }

        switch (entity.getInveEstadoValoracion().getEsvCodigo()) {
            case "a":
            case "b":
                em.persist(seguimiento);
                em.merge(entity.getInveLector());
                break;
            case "c":
                InveLector nuevoLector = new InveLector();
                nuevoLector.setInveProyecto(entity.getInveLector().getInveProyecto());
                nuevoLector.setPrinPersona(entity.getInveLector().getPrinPersona());
                nuevoLector.setInveRol(entity.getInveLector().getInveRol());
                nuevoLector.setLecNumero((short) (entity.getInveLector().getLecNumero() + 1));
                nuevoLector.setLecAceptacion("S");
                nuevoLector.setLecFechaCrea(entity.getValFechaCrea());
                nuevoLector.setLecUsuarioCrea(entity.getValUsuarioCrea());
                em.persist(nuevoLector);
                em.persist(seguimiento);
                break;
            case "d":
                em.merge(entity.getInveLector());
                em.persist(seguimiento);
                break;
            case "1":
            case "2":
            case "3":
                em.merge(entity.getInveLector());
                break;

        }

//        if (doc.getDocModOrigen() != null) {
//            doc.setDocEntidadCodigo(entity.getValCodigo());
//            prinDocumentoFacade.create(doc);
//        }
    }

}
