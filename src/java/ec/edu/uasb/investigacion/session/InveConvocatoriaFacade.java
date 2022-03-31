/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatGrupo;
import ec.edu.uasb.investigacion.entities.InveConvocatGrupoPK;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import org.joda.time.LocalDate;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveConvocatoriaFacade extends AbstractFacade<InveConvocatoria> implements InveConvocatoriaFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveConvocatoriaFacade() {
        super(InveConvocatoria.class);
    }

    @Override
    public List<InveConvocatoria> findBy(Short anio) {
        Query q = em.createQuery("SELECT i FROM InveConvocatoria i WHERE i.cvoAnioAcad = :cvoAnioAcad").setParameter("cvoAnioAcad", anio);
        List<InveConvocatoria> temp = q.getResultList();
        for (InveConvocatoria convo : temp) {
            StringBuilder sb = new StringBuilder();
            for (InveConvocatGrupo cgr : convo.getInveConvocatGrupoCollection()) {
                sb.append(cgr.getInveGrupo().getGrpNombre()).append(" <span style='color:blue;'>|</span> ");
            }
            convo.setGrupos(sb.toString());
        }
        return temp;
    }

     @Override
    public InveConvocatoria findByGrupo(Short grupo) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Query q = em.createQuery("SELECT g.inveConvocatoria FROM InveConvocatGrupo g WHERE g.inveGrupo.grpCodigo = :grupo and :fecha_hoy between g.inveConvocatoria.cvoFechaInicio and g.inveConvocatoria.cvoFechaFin");
        try {
            Date fecha_hoy = dateFormat.parse(dateFormat.format(new Date()));
            q.setParameter("grupo", grupo).setParameter("fecha_hoy", fecha_hoy);
        } catch (ParseException ex) {
            Logger.getLogger(InveConvocatoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (InveConvocatoria) q.getSingleResult();
    }

    //<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public void create(InveConvocatoria entity, Short[] selectedGrupos) {
        super.create(entity);
        em.flush();
        entity.setInveConvocatGrupoCollection(new ArrayList<InveConvocatGrupo>());
        for (Short grp : selectedGrupos) {
            InveConvocatGrupo cgr = new InveConvocatGrupo(new InveConvocatGrupoPK(entity.getCvoCodigo(), grp));
            cgr.setInveGrupo(new InveGrupo(grp));
            cgr.setInveConvocatoria(entity);
            entity.getInveConvocatGrupoCollection().add(cgr);
        }
        super.edit(entity);
    }

    @Override
    public void edit(InveConvocatoria entity, Short[] selectedGrupos) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaDelete delete = cb.createCriteriaDelete(InveConvocatGrupo.class);
        Root e = delete.from(InveConvocatGrupo.class);
        delete.where(cb.equal(e.get("inveConvocatGrupoPK").get("cvoCodigo"), entity.getCvoCodigo()));
        if (entity.getInveConvocatGrupoCollection() != null) {
            entity.getInveConvocatGrupoCollection().removeAll(new ArrayList(entity.getInveConvocatGrupoCollection()));
        }
        em.createQuery(delete).executeUpdate();
        for (Short grp : selectedGrupos) {
            InveConvocatGrupo cgr = new InveConvocatGrupo(new InveConvocatGrupoPK(entity.getCvoCodigo(), grp));
            cgr.setInveGrupo(new InveGrupo(grp));
            cgr.setInveConvocatoria(entity);
            entity.getInveConvocatGrupoCollection().add(cgr);
            em.persist(cgr);
        }
        em.merge(entity);
    }
//</editor-fold>

}
