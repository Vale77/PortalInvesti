/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveRubro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InvePresupuestoFacadeLocal {

    void create(InvePresupuesto invePresupuesto);

    void edit(InvePresupuesto invePresupuesto);

    void remove(InvePresupuesto invePresupuesto);

    InvePresupuesto find(Object id);

    List<InvePresupuesto> findAll();

    List<InvePresupuesto> findRange(int[] range);

    int count();

    public List<InvePresupuesto> getPresupuestoBy(InveRubro inveRubro);
}
