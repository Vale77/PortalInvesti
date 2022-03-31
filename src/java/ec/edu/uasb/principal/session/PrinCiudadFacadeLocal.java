/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinCiudad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinCiudadFacadeLocal {

    void create(PrinCiudad prinCiudad);

    void edit(PrinCiudad prinCiudad);

    void remove(PrinCiudad prinCiudad);

    PrinCiudad find(Object id);

    List<PrinCiudad> findAll();

    List<PrinCiudad> findRange(int[] range);

    int count();

    public List<PrinCiudad> findAllorde(Short paiCodigo);

}
