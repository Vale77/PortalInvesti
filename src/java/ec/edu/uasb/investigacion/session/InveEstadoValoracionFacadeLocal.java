/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveEstadoValoracionFacadeLocal {

    void create(InveEstadoValoracion inveEstadoValoracion);

    void edit(InveEstadoValoracion inveEstadoValoracion);

    void remove(InveEstadoValoracion inveEstadoValoracion);

    InveEstadoValoracion find(Object id);

    List<InveEstadoValoracion> findAll();

    List<InveEstadoValoracion> findRange(int[] range);

    int count();

    public List<InveEstadoValoracion> getEstadoBy(InveRol inveRol);
}
