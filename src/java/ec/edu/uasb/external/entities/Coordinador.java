/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author xavier.duque
 */
@Entity
public class Coordinador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PER_CODIGO")
    private Long perCodigo;
    @Column(name = "NOMBRE_COORDINADOR")
    private String nombreCoordinador;

    public Coordinador() {
    }

    public Coordinador(Long perCodigo, String nombreCoordinador) {
        this.perCodigo = perCodigo;
        this.nombreCoordinador = nombreCoordinador;
    }

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getNombreCoordinador() {
        return nombreCoordinador;
    }

    public void setNombreCoordinador(String nombreCoordinador) {
        this.nombreCoordinador = nombreCoordinador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.perCodigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinador other = (Coordinador) obj;
        if (!Objects.equals(this.perCodigo, other.perCodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinador{" + "perCodigo=" + perCodigo + ", nombreCoordinador=" + nombreCoordinador + '}';
    }

}
