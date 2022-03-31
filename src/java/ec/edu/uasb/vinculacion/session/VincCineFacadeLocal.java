/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.vinculacion.session;

import ec.edu.uasb.vinculacion.entities.VincCine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface VincCineFacadeLocal {

    void create(VincCine vincCine);

    void edit(VincCine vincCine);

    void remove(VincCine vincCine);

    VincCine find(Object id);

    List<VincCine> findAll();

    List<VincCine> findRange(int[] range);

    int count();

    public List<VincCine> findByPadre(VincCine padre);

    public List<VincCine> findBy(String padre);

}
