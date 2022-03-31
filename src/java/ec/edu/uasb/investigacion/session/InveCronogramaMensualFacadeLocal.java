/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveCronogramaMensual;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveCronogramaMensualFacadeLocal {

    void create(InveCronogramaMensual inveCronogramaMensual);

    void edit(InveCronogramaMensual inveCronogramaMensual);

    void remove(InveCronogramaMensual inveCronogramaMensual);

    InveCronogramaMensual find(Object id);

    List<InveCronogramaMensual> findAll();

    List<InveCronogramaMensual> findRange(int[] range);

    int count();
    
}
