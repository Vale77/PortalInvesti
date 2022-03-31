/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveTipoConvocatoriaFacadeLocal {

    void create(InveTipoConvocatoria inveTipoConvocatoria);

    void edit(InveTipoConvocatoria inveTipoConvocatoria);

    void remove(InveTipoConvocatoria inveTipoConvocatoria);

    InveTipoConvocatoria find(Object id);

    List<InveTipoConvocatoria> findAll();

    List<InveTipoConvocatoria> findRange(int[] range);

    int count();
    
}
