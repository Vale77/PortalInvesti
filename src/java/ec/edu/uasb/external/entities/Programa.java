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
import javax.persistence.Table;

/**
 *
 * @author xavier.duque
 */
@Entity
@Table(name = "PROGRAMA", schema = "interfaseOcu.")
public class Programa implements Serializable {

    private static final long serialVersionUID = 101L;
    @Id
    @Column(name = "PRG_CODIGO")
    private Long prgCodigo;
    @Column(name = "NOMBRE_PROGRAMA")
    private String nombrePrograma;

    public Long getPrgCodigo() {
        return prgCodigo;
    }

    public void setPrgCodigo(Long prgCodigo) {
        this.prgCodigo = prgCodigo;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Programa() {

    }

    public Programa(Long prgCodigo, String nombrePrograma) {
        this.prgCodigo = prgCodigo;
        this.nombrePrograma = nombrePrograma;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.prgCodigo);
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
        final Programa other = (Programa) obj;
        if (!Objects.equals(this.prgCodigo, other.prgCodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Programa{" + "prgCodigo=" + prgCodigo + ", nombrePrograma=" + nombrePrograma + '}';
    }

}
