/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.SecretariaArea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author xavier.duque
 */
@Local
public interface SecretariaAreaFacadeLocal {

    void create(SecretariaArea secretariaArea);

    void edit(SecretariaArea secretariaArea);

    void remove(SecretariaArea secretariaArea);

    SecretariaArea find(Object id);

    List<SecretariaArea> findAll();

    List<SecretariaArea> findRange(int[] range);

    int count();

    public List<SecretariaArea> findAllByTipo(String tipo);

    
}
