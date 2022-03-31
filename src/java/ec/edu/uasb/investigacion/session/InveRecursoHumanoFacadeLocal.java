/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import ec.edu.uasb.investigacion.entities.InveRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveRecursoHumanoFacadeLocal {

    void create(InveRecursoHumano inveRecursoHumano);

    void edit(InveRecursoHumano inveRecursoHumano);

    void remove(InveRecursoHumano inveRecursoHumano);

    InveRecursoHumano find(Object id);

    List<InveRecursoHumano> findAll();

    List<InveRecursoHumano> findRange(int[] range);

    int count();

    public List<InveRecursoHumano> getEstadoBy(InveRol inveRol);
}
