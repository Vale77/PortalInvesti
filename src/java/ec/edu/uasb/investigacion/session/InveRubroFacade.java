/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
import ec.edu.uasb.investigacion.entities.InveRubroGrupoPK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveRubroFacade extends AbstractFacade<InveRubro> implements InveRubroFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveRubroFacade() {
        super(InveRubro.class);
    }

    class InveRubroGrupoComp implements Comparator<InveRubroGrupo> {

        @Override
        public int compare(InveRubroGrupo e1, InveRubroGrupo e2) {
            return (e1.getInveGrupo().getGrpNombre()).compareTo(e2.getInveGrupo().getGrpNombre());
        }
    }

    @Override
    public List<InveRubro> findAll() {
        List<InveRubro> temp = super.findAll();
        for (InveRubro next : temp) {
            ArrayList<InveRubroGrupo> grupos = new ArrayList(next.getInveRubroGrupoCollection());
            Collections.sort(grupos, new InveRubroGrupoComp());
            StringBuilder sb = new StringBuilder();
            for (InveRubroGrupo cgr : grupos) {
                sb.append(cgr.getInveGrupo().getGrpNombre()).append(" <span style='color:blue;'>|</span> ");
            }
            next.setGrupos(sb.toString());
        }
        return temp;
    }

    @Override
    public void create(InveRubro entity, Short[] selectedGrupos) {
        super.create(entity);
        em.flush();
        entity.setInveRubroGrupoCollection(new ArrayList<InveRubroGrupo>());
        for (Short grp : selectedGrupos) {
            InveRubroGrupo rg = new InveRubroGrupo(new InveRubroGrupoPK(entity.getRubCodigo(), grp));
            rg.setInveGrupo(new InveGrupo(grp));
            rg.setInveRubro(entity);
            entity.getInveRubroGrupoCollection().add(rg);
        }
        super.edit(entity);
    }

    @Override
    public void edit(InveRubro entity, Short[] selectedGrupos) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaDelete delete = cb.createCriteriaDelete(InveRubroGrupo.class);
        Root e = delete.from(InveRubroGrupo.class);
        delete.where(cb.equal(e.get("inveRubroGrupoPK").get("rubCodigo"), entity.getRubCodigo()));
        if (entity.getInveRubroGrupoCollection() != null) {
            entity.getInveRubroGrupoCollection().removeAll(new ArrayList(entity.getInveRubroGrupoCollection()));
        }
        em.createQuery(delete).executeUpdate();
        em.flush();
        for (Short grp : selectedGrupos) {
            InveRubroGrupo rg = new InveRubroGrupo(new InveRubroGrupoPK(grp, entity.getRubCodigo()));
            rg.setInveGrupo(new InveGrupo(grp));
            rg.setInveRubro(entity);
            entity.getInveRubroGrupoCollection().add(rg);
            em.persist(rg);
        }
        em.merge(entity);
    }

}
