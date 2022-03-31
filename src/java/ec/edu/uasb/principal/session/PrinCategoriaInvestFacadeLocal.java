/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinCategoriaInvestFacadeLocal {

    void create(PrinCategoriaInvest prinCategoriaInvest);

    void edit(PrinCategoriaInvest prinCategoriaInvest);

    void remove(PrinCategoriaInvest prinCategoriaInvest);

    PrinCategoriaInvest find(Object id);

    List<PrinCategoriaInvest> findAll();

    List<PrinCategoriaInvest> findRange(int[] range);

    int count();
    
}
