/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.principal.entities.PrinDocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveValoracionFacadeLocal {

    void create(InveValoracion inveValoracion);

    void edit(InveValoracion inveValoracion);

    void remove(InveValoracion inveValoracion);

    InveValoracion find(Object id);

    List<InveValoracion> findAll();

    List<InveValoracion> findRange(int[] range);

    int count();

    public List<InveValoracion> getValoracionBy(InveEstadoValoracion iev);

    public void edit(InveValoracion entity, PrinDocumento doc, InveSeguimiento seguimiento);

    public List<InveValoracion> getValoracionesBy(Short anio);
    
    public List<InveValoracion> getValorConvocatoriaBy(InveConvocatoria convoc);
}
