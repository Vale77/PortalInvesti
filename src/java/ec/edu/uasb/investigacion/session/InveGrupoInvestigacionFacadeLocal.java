/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveGrupoInvestigacionFacadeLocal {

    void create(InveGrupoInvestigacion inveGrupoInvestigacion);

    void edit(InveGrupoInvestigacion inveGrupoInvestigacion);

    void remove(InveGrupoInvestigacion inveGrupoInvestigacion);

    InveGrupoInvestigacion find(Object id);

    List<InveGrupoInvestigacion> findAll();

    List<InveGrupoInvestigacion> findRange(int[] range);

    int count();
    
}
