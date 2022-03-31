/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveRubro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveRubroFacadeLocal {

    void create(InveRubro inveRubro);

    void edit(InveRubro inveRubro);

    void remove(InveRubro inveRubro);

    InveRubro find(Object id);

    List<InveRubro> findAll();

    List<InveRubro> findRange(int[] range);

    int count();

    public void create(InveRubro entity, Short[] selectedGrupos);

    public void edit(InveRubro entity, Short[] selectedGrupos);
    
}
