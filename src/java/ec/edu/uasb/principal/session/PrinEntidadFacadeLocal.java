/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinEntidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinEntidadFacadeLocal {

    void create(PrinEntidad prinEntidad);

    void edit(PrinEntidad prinEntidad);

    void remove(PrinEntidad prinEntidad);

    PrinEntidad find(Object id);

    List<PrinEntidad> findAll();

    List<PrinEntidad> findRange(int[] range);

    int count();

    public List<PrinEntidad> findAllUniversidades();
    
}
