/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.external.entities.Programa;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveProgramaProyecto;
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
public class InveProgramaProyectoFacade extends AbstractFacade<InveProgramaProyecto> implements InveProgramaProyectoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveProgramaProyectoFacade() {
        super(InveProgramaProyecto.class);
    }

    @Override
    public List<InveProgramaProyecto> findBy(InveCarta inveCarta) {
        List<InveProgramaProyecto> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("DECLARE @PROGRAMAS TABLE ( PRG_CODIGO Integer,NOMBRE_PROGRAMA varchar(500));")
                .append(" INSERT INTO @PROGRAMAS( PRG_CODIGO ,NOMBRE_PROGRAMA ) ")
                .append(" select PROGRAMA.PRG_CODIGO,PROGRAMA.NOMBRE_PROGRAMA from  PROGRAMA WHERE ARE_CODIGO = ? ")
                .append(" SELECT DISTINCT INVE_PROGRAMA_PROYECTO.*,PROGRAMAS.* ")
                .append(" FROM INVE_PROGRAMA_PROYECTO ")
                .append(" INNER JOIN @PROGRAMAS PROGRAMAS  ON (INVE_PROGRAMA_PROYECTO.PRG_CODIGO = PROGRAMAS.PRG_CODIGO)")
                .append(" WHERE INVE_PROGRAMA_PROYECTO.PRY_CODIGO = ? ");
        Query q = em.createNativeQuery(sb.toString(), "progProyResults");
        q.setParameter(2, inveCarta.getPryCodigo()).setParameter(1, inveCarta.getInveProyecto().getPryAreaAcad());
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveProgramaProyecto ipp = (InveProgramaProyecto) reg[0];
            Programa prog = (Programa) reg[1];
            ipp.setPrograma(prog);
            lista.add(ipp);
        }
        return lista;
    }

}
