/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinPais;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinPaisFacadeLocal {

    void create(PrinPais prinPais);

    void edit(PrinPais prinPais);

    void remove(PrinPais prinPais);

    PrinPais find(Object id);

    List<PrinPais> findAll();

    List<PrinPais> findRange(int[] range);

    int count();

    public List<PrinPais> findAllorde();
    
}
