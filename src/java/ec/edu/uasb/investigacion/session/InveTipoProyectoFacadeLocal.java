/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveTipoProyectoFacadeLocal {

    void create(InveTipoProyecto inveTipoProyecto);

    void edit(InveTipoProyecto inveTipoProyecto);

    void remove(InveTipoProyecto inveTipoProyecto);

    InveTipoProyecto find(Object id);

    List<InveTipoProyecto> findAll();

    List<InveTipoProyecto> findRange(int[] range);

    int count();
    
}
