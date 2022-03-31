/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.principal.entities.PrinPersona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveLectorFacadeLocal {

    void create(InveLector inveLector);

    void edit(InveLector inveLector);

    void remove(InveLector inveLector);

    InveLector find(Object id);

    List<InveLector> findAll();

    List<InveLector> findRange(int[] range);

    int count();

    List<PrinPersona> findAllDocentes();

    List<InveLector> findBy(Short anio);
    
    List<InveLector> findValoracionBy(InveConvocatoria convoc);

    void edit(InveLector entity, InveSeguimiento seguimiento);

    public List<InveLector> findValFinalByProyecto(InveProyecto inveProyecto);

    public List<PrinPersona> findAllLectores();

    public List<PrinPersona> findAllInvestigadores();

    public List<InveLector> findByRol(InveProyecto inveProyecto, Short rolLec);

}
