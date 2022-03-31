/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.AreaAcademica;
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
public class AreaAcademicaFacade extends AbstractFacade<AreaAcademica> implements AreaAcademicaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaAcademicaFacade() {
        super(AreaAcademica.class);
    }

    @Override
    public List<AreaAcademica> getListaAreasInteres() {
        Query q = em.createNativeQuery("SELECT ARE_CODIGO,NOMBRE_AREA FROM interfaseOcu.dbo.AREA ORDER BY 2", AreaAcademica.class);
        return q.getResultList();
    }

    @Override
    public List<AreaAcademica> getListaAreasDepartamentos() {
        Query q = em.createNativeQuery("SELECT ARE_CODIGO,NOMBRE_AREA FROM interfaseOcu.dbo.AREA "
                + "UNION SELECT DEP_CODIGO, DEP_NOMBRE  FROM interfaseOcu..DEPARTAMENTO_ADMI ORDER BY 2", AreaAcademica.class);
        return q.getResultList();
    }

    @Override
    public List<AreaAcademica> getAreasByUsuario(Long codUsr) {
        Query q = em.createNativeQuery("SELECT DISTINCT ARE_CODIGO, NOMBRE_AREA FROM V_USUARIO1 "
                + " INNER JOIN (SELECT distinct ARE_CODIGO,NOMBRE_AREA  FROM interfaseOcu.dbo.AREA "
                + " UNION SELECT DEP_CODIGO, DEP_NOMBRE  FROM interfaseOcu.dbo.DEPARTAMENTO_ADMI) AREA ON (ISNULL(V_USUARIO1.COD_AREA,V_USUARIO1.DEP_CODIGO) = AREA.ARE_CODIGO)"
                + " WHERE CODIGO = ?", AreaAcademica.class).setParameter(1, codUsr);
        return q.getResultList();

    }

}
