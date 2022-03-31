/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveGrupoFacadeLocal {

    void create(InveGrupo inveGrupo);

    void edit(InveGrupo inveGrupo);

    void remove(InveGrupo inveGrupo);

    InveGrupo find(Object id);

    List<InveGrupo> findAll();

    List<InveGrupo> findRange(int[] range);

    int count();

}
