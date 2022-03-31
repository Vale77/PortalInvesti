/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLectorContratado;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveLectorContratadoFacadeLocal {

    void create(InveLectorContratado inveLectorContratado);

    void edit(InveLectorContratado inveLectorContratado);

    void remove(InveLectorContratado inveLectorContratado);

    InveLectorContratado find(Object id);

    List<InveLectorContratado> findAll();

    List<InveLectorContratado> findRange(int[] range);

    int count();

    public void createLectorContratado(PrinPersona persona, PrinTitulo prinTitulo);

    public InveLectorContratado findById(String identificacion);

}
