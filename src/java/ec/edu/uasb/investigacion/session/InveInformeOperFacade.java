/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
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
public class InveInformeOperFacade extends AbstractFacade<InveInformeOper> implements InveInformeOperFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveInformeOperFacade() {
        super(InveInformeOper.class);
    }

    @Override
    public List<InveInformeOper> findBy(Short anio) {
        List<InveInformeOper> lista = new ArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT INVE_INFORME_OPER.*,AREA.*, ")
                .append(" isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1)  SEG_CODIGO, ")
                .append(" INVE_SEGUIMIENTO.* ")
                .append(" FROM INVE_PROYECTO  ")
                .append(" INNER JOIN AREA ON (INVE_PROYECTO.PRY_AREA_ACAD = AREA.ARE_CODIGO) ")
                .append(" INNER JOIN (SELECT INVE_VALORACION.*,INVE_LECTOR.PRY_CODIGO from INVE_VALORACION ")
                .append(" INNER JOIN INVE_LECTOR ON (INVE_LECTOR.LEC_CODIGO = INVE_VALORACION.LEC_CODIGO  AND INVE_LECTOR.ROL_CODIGO = 1 AND INVE_VALORACION.ESV_CODIGO IN ('a', 'b'))) APROBADO ")
                .append(" ON (APROBADO.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO )  ")
                .append(" LEFT OUTER JOIN INVE_INFORME_OPER ON (INVE_INFORME_OPER.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND APROBADO.PRY_CODIGO = INVE_INFORME_OPER.PRY_CODIGO  ) ")
                .append(" LEFT OUTER JOIN (select ROW_NUMBER ()OVER (PARTITION BY SEG.PRY_CODIGO ORDER BY SEG.SEG_NRO_COMUNIC desc) NUM_FILA ,* ")
                .append("       from INVE_SEGUIMIENTO SEG where SEG.TSE_CODIGO = 3 AND SEG.SEG_ESTADO_REG = 'A') INVE_SEGUIMIENTO ")
                .append(" ON (INVE_SEGUIMIENTO.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO  AND INVE_SEGUIMIENTO.NUM_FILA = 1) ")
                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ? AND INVE_INFORME_OPER.IOP_FECHA_CREA is not null ")
                .append(" ORDER BY INVE_PROYECTO.PRY_CODIGO_CI ; ");
        Query q = em.createNativeQuery(sql.toString(), "infoOperaResults");
        q.setParameter(1, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveInformeOper io = (InveInformeOper) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            io.setArea(area);
            io.setInveSeguimiento((InveSeguimiento) reg[2]);
            lista.add(io);
        }
        return lista;

    }

     @Override
    public List<InveInformeOper> findInformeBy(InveConvocatoria convoc) {
        List<InveInformeOper> lista = new ArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT INVE_INFORME_OPER.*,AREA.*, ")
                .append(" isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1)  SEG_CODIGO, ")
                .append(" INVE_SEGUIMIENTO.* ")
                .append(" FROM INVE_PROYECTO  ")
                .append(" INNER JOIN AREA ON (INVE_PROYECTO.PRY_AREA_ACAD = AREA.ARE_CODIGO) ")
                .append(" INNER JOIN (SELECT INVE_VALORACION.*,INVE_LECTOR.PRY_CODIGO from INVE_VALORACION ")
                .append(" INNER JOIN INVE_LECTOR ON (INVE_LECTOR.LEC_CODIGO = INVE_VALORACION.LEC_CODIGO  AND INVE_LECTOR.ROL_CODIGO = 1 AND INVE_VALORACION.ESV_CODIGO IN ('a', 'b'))) APROBADO ")
                .append(" ON (APROBADO.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO )  ")
                .append(" LEFT OUTER JOIN INVE_INFORME_OPER ON (INVE_INFORME_OPER.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO AND APROBADO.PRY_CODIGO = INVE_INFORME_OPER.PRY_CODIGO  ) ")
                .append(" LEFT OUTER JOIN (select ROW_NUMBER ()OVER (PARTITION BY SEG.PRY_CODIGO ORDER BY SEG.SEG_NRO_COMUNIC desc) NUM_FILA ,* ")
                .append("       from INVE_SEGUIMIENTO SEG where SEG.TSE_CODIGO = 3 AND SEG.SEG_ESTADO_REG = 'A') INVE_SEGUIMIENTO ")
                .append(" ON (INVE_SEGUIMIENTO.PRY_CODIGO = INVE_PROYECTO.PRY_CODIGO  AND INVE_SEGUIMIENTO.NUM_FILA = 1) ")
                .append(" WHERE INVE_PROYECTO.CVO_CODIGO = ? AND INVE_INFORME_OPER.IOP_FECHA_CREA is not null ")
                .append(" ORDER BY INVE_PROYECTO.PRY_CODIGO_CI ; ");
        Query q = em.createNativeQuery(sql.toString(), "infoOperaResults");
        q.setParameter(1, convoc.getCvoCodigo());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveInformeOper io = (InveInformeOper) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            io.setArea(area);
            io.setInveSeguimiento((InveSeguimiento) reg[2]);
            lista.add(io);
        }
        return lista;

    }
    @Override
    public void create(InveInformeOper entity) {
        super.create(entity);
        em.merge(entity.getInveProyecto());
    }

    @Override
    public void edit(InveInformeOper entity) {
        super.edit(entity); 
        em.merge(entity.getInveProyecto());
    }

}
