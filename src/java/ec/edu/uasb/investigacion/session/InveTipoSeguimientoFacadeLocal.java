/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveTipoSeguimientoFacadeLocal {

    void create(InveTipoSeguimiento inveTipoSeguimiento);

    void edit(InveTipoSeguimiento inveTipoSeguimiento);

    void remove(InveTipoSeguimiento inveTipoSeguimiento);

    InveTipoSeguimiento find(Object id);

    List<InveTipoSeguimiento> findAll();

    List<InveTipoSeguimiento> findRange(int[] range);

    int count();
    
}
