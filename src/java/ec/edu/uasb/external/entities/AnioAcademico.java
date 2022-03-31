/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author xavier.duque
 */
@Entity
@Table(name = "CICLO_ACADEMICO",schema = "interfaseOcu.")
public class AnioAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ANIO")
    private int anio;
    @Column(name = "NOMBRE_CICLO")
    private String nombreCiclo;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

    public AnioAcademico() {
    }

    public AnioAcademico(int anio, String nombreCiclo) {
        this.anio = anio;
        this.nombreCiclo = nombreCiclo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.anio;
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
        final AnioAcademico other = (AnioAcademico) obj;
        if (this.anio != other.anio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnioAcademico{" + "anio=" + anio + ", nombreCiclo=" + nombreCiclo + '}';
    }

}
