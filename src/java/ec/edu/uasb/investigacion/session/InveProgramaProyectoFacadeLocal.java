/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveProgramaProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveProgramaProyectoFacadeLocal {

    void create(InveProgramaProyecto inveProgramaProyecto);

    void edit(InveProgramaProyecto inveProgramaProyecto);

    void remove(InveProgramaProyecto inveProgramaProyecto);

    InveProgramaProyecto find(Object id);

    List<InveProgramaProyecto> findAll();

    List<InveProgramaProyecto> findRange(int[] range);

    int count();

    public List<InveProgramaProyecto> findBy(InveCarta inveCarta);
}
