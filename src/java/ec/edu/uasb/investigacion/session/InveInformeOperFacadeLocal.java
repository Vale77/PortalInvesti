/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveInformeOperFacadeLocal {

    void create(InveInformeOper inveInformeOper);

    void edit(InveInformeOper inveInformeOper);

    void remove(InveInformeOper inveInformeOper);

    InveInformeOper find(Object id);

    List<InveInformeOper> findAll();

    List<InveInformeOper> findRange(int[] range);

    int count();

    public List<InveInformeOper> findBy(Short anio);
    
    public List<InveInformeOper> findInformeBy(InveConvocatoria convoc);

    
}
