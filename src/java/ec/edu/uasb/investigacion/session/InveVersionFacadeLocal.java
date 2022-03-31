/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveVersion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveVersionFacadeLocal {

    void create(InveVersion inveVersion);

    void edit(InveVersion inveVersion);

    void remove(InveVersion inveVersion);

    InveVersion find(Object id);

    List<InveVersion> findAll();

    List<InveVersion> findRange(int[] range);

    int count();
    
}
