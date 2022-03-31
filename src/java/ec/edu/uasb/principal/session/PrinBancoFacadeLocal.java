/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinBanco;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinBancoFacadeLocal {

    void create(PrinBanco prinBanco);

    void edit(PrinBanco prinBanco);

    void remove(PrinBanco prinBanco);

    PrinBanco find(Object id);

    List<PrinBanco> findAll();

    List<PrinBanco> findRange(int[] range);

    int count();
    
}
