/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveFuenteFinan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveFuenteFinanFacadeLocal {

    void create(InveFuenteFinan inveFuenteFinan);

    void edit(InveFuenteFinan inveFuenteFinan);

    void remove(InveFuenteFinan inveFuenteFinan);

    InveFuenteFinan find(Object id);

    List<InveFuenteFinan> findAll();

    List<InveFuenteFinan> findRange(int[] range);

    int count();
    
}
