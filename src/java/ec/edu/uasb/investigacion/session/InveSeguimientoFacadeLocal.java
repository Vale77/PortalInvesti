/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveSeguimientoFacadeLocal {

    void create(InveSeguimiento inveSeguimiento);

    void edit(InveSeguimiento inveSeguimiento);

    void remove(InveSeguimiento inveSeguimiento);

    InveSeguimiento find(Object id);

    List<InveSeguimiento> findAll();

    List<InveSeguimiento> findRange(int[] range);

    int count();

    public void create(InveSeguimiento entity, List<InveLector> listaLectorFinal, boolean aprobado);

    public String getMessage(InveSeguimiento entity);

    public BigDecimal obtenerMontoxBeca(InveProyecto proyecto);

}
