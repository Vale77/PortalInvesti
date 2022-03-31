/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveLineaInvestProyec;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveLineaInvestProyecFacadeLocal {

    void create(InveLineaInvestProyec inveLineaInvestProyec);

    void edit(InveLineaInvestProyec inveLineaInvestProyec);

    void remove(InveLineaInvestProyec inveLineaInvestProyec);

    InveLineaInvestProyec find(Object id);

    List<InveLineaInvestProyec> findAll();

    List<InveLineaInvestProyec> findRange(int[] range);

    int count();

    public List<InveLineaInvestProyec> findBy(Short area);
}
