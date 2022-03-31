/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class PrinCiudadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_CODIGO")
    private Short paiCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIU_CODIGO")
    private Integer ciuCodigo;

    public PrinCiudadPK() {
    }

    public PrinCiudadPK(Short paiCodigo, Integer ciuCodigo) {
        this.paiCodigo = paiCodigo;
        this.ciuCodigo = ciuCodigo;
    }

    public Short getPaiCodigo() {
        return paiCodigo;
    }

    public void setPaiCodigo(Short paiCodigo) {
        this.paiCodigo = paiCodigo;
    }

    public Integer getCiuCodigo() {
        return ciuCodigo;
    }

    public void setCiuCodigo(Integer ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.paiCodigo);
        hash = 59 * hash + Objects.hashCode(this.ciuCodigo);
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
        final PrinCiudadPK other = (PrinCiudadPK) obj;
        if (!Objects.equals(this.paiCodigo, other.paiCodigo)) {
            return false;
        }
        if (!Objects.equals(this.ciuCodigo, other.ciuCodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinCiudadPK[ paiCodigo=" + paiCodigo + ", ciuCodigo=" + ciuCodigo + " ]";
    }

}
