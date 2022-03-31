/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveRolFacadeLocal {

    void create(InveRol inveRol);

    void edit(InveRol inveRol);

    void remove(InveRol inveRol);

    InveRol find(Object id);

    List<InveRol> findAll();

    List<InveRol> findRange(int[] range);

    int count();
    
}
