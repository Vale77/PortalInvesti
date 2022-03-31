/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinTipoDedicacionFacadeLocal {

    void create(PrinTipoDedicacion prinTipoDedicacion);

    void edit(PrinTipoDedicacion prinTipoDedicacion);

    void remove(PrinTipoDedicacion prinTipoDedicacion);

    PrinTipoDedicacion find(Object id);

    List<PrinTipoDedicacion> findAll();

    List<PrinTipoDedicacion> findRange(int[] range);

    int count();
    
}
