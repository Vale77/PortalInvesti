/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class InveEquipoMultiPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PRY_CODIGO")
    private Long pryCodigo;
    @Basic(optional = false)
    @Column(name = "ROL_CODIGO")
    private Short rolCodigo;

    public InveEquipoMultiPK() {
    }

    public InveEquipoMultiPK(Long pryCodigo, Short rolCodigo) {
        this.pryCodigo = pryCodigo;
        this.rolCodigo = rolCodigo;
    }

    public Long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public Short getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(Short rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.pryCodigo);
        hash = 47 * hash + Objects.hashCode(this.rolCodigo);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveEquipoMultiPK)) {
            return false;
        }
        InveEquipoMultiPK other = (InveEquipoMultiPK) object;
        if (this.pryCodigo != other.pryCodigo) {
            return false;
        }
        if (this.rolCodigo != other.rolCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication10.InveEquipoMultiPK[ pryCodigo=" + pryCodigo + ", rolCodigo=" + rolCodigo + " ]";
    }

}
