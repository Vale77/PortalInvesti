/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveCronogramaMensual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveCronogramaMensualFacade extends AbstractFacade<InveCronogramaMensual> implements InveCronogramaMensualFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveCronogramaMensualFacade() {
        super(InveCronogramaMensual.class);
    }
    
}
