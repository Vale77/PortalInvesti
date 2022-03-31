/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveDisciplinaCientificaFacadeLocal {

    void create(InveDisciplinaCientifica inveDisciplinaCientifica);

    void edit(InveDisciplinaCientifica inveDisciplinaCientifica);

    void remove(InveDisciplinaCientifica inveDisciplinaCientifica);

    InveDisciplinaCientifica find(Object id);

    List<InveDisciplinaCientifica> findAll();

    List<InveDisciplinaCientifica> findRange(int[] range);

    int count();

}
