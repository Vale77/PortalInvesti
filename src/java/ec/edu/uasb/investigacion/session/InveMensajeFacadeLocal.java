/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveMensaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveMensajeFacadeLocal {

    void create(InveMensaje inveMensaje);

    void edit(InveMensaje inveMensaje);

    void remove(InveMensaje inveMensaje);

    InveMensaje find(Object id);

    List<InveMensaje> findAll();

    List<InveMensaje> findRange(int[] range);

    int count();
    
}
