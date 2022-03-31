/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class InvePresupuestoPK implements Serializable {

    @Column(name = "PRY_CODIGO")
    private Long pryCodigo;
    @Column(name = "RUB_CODIGO")
    private Short rubCodigo;

    public InvePresupuestoPK() {
    }

    public InvePresupuestoPK(Long pryCodigo, Short rubCodigo) {
        this.pryCodigo = pryCodigo;
        this.rubCodigo = rubCodigo;
    }

    public Long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public Short getRubCodigo() {
        return rubCodigo;
    }

    public void setRubCodigo(Short rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.pryCodigo);
        hash = 53 * hash + Objects.hashCode(this.rubCodigo);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvePresupuestoPK)) {
            return false;
        }
        InvePresupuestoPK other = (InvePresupuestoPK) object;
        if (this.pryCodigo != other.pryCodigo) {
            return false;
        }
        if (this.rubCodigo != other.rubCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InvePresupuestoPK[ pryCodigo=" + pryCodigo + ", rubCodigo=" + rubCodigo + " ]";
    }

}
