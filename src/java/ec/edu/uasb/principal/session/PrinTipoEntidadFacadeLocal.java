/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinTipoEntidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinTipoEntidadFacadeLocal {

    void create(PrinTipoEntidad prinTipoEntidad);

    void edit(PrinTipoEntidad prinTipoEntidad);

    void remove(PrinTipoEntidad prinTipoEntidad);

    PrinTipoEntidad find(Object id);

    List<PrinTipoEntidad> findAll();

    List<PrinTipoEntidad> findRange(int[] range);

    int count();
    
}
