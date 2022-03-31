/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.vinculacion.session;

import ec.edu.uasb.vinculacion.entities.VincCine;
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
public class VincCineFacade extends AbstractFacade<VincCine> implements VincCineFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VincCineFacade() {
        super(VincCine.class);
    }

    @Override
    public List<VincCine> findByPadre(VincCine padre) {
        Query q = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH Arbol (CIN_CODIGO, CIN_CIN_CODIGO, CIN_CAMPO,nivel) AS( ")
                .append(" SELECT CIN_CODIGO, CIN_CIN_CODIGO, CIN_CAMPO,0 FROM VINC_CINE WHERE CIN_CIN_CODIGO IS NULL ")
                .append(" UNION ALL ")
                .append(" SELECT VINC_CINE.CIN_CODIGO , VINC_CINE.CIN_CIN_CODIGO , VINC_CINE.CIN_CAMPO ,nivel + 1 ")
                .append(" FROM VINC_CINE INNER JOIN Arbol ON VINC_CINE.CIN_CIN_CODIGO = Arbol.CIN_CODIGO )")
                .append(" SELECT * FROM Arbol WHERE isnull(CIN_CIN_CODIGO,0) = isnull(?,0) ORDER BY CIN_CODIGO,CIN_CAMPO ");
        if (padre == null) {
            q = em.createNativeQuery(sql.toString(), "CineResults").setParameter(1, "0");
        } else {
            q = em.createNativeQuery(sql.toString(), "CineResults").setParameter(1, padre.getCinCodigo());
        }
        return q.getResultList();
    }

    @Override
    public List<VincCine> findBy(String padre) {
        Query q;
        if (padre == null) {
            q = em.createQuery("select v from VincCine v where v.vincCine is null order by v.cinCampo");
        } else {
            q = em.createQuery("select v from VincCine v where v.vincCine.cinCodigo = :cinCodigo order by v.cinCampo");
            q.setParameter("cinCodigo", padre);
        }
        return q.getResultList();
    }
}
