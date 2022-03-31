/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinTituloFacadeLocal {

    void create(PrinTitulo prinTitulo);

    void edit(PrinTitulo prinTitulo);

    void remove(PrinTitulo prinTitulo);

    PrinTitulo find(Object id);

    List<PrinTitulo> findAll();

    List<PrinTitulo> findRange(int[] range);

    int count();

    public List<PrinTitulo> findTitulosBy(PrinPersona persona);
}
