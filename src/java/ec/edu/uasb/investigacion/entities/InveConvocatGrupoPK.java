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
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class InveConvocatGrupoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CVO_CODIGO")
    private Long cvoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRP_CODIGO")
    private Short grpCodigo;

    public InveConvocatGrupoPK() {
    }

    public InveConvocatGrupoPK(Long cvoCodigo, short grpCodigo) {
        this.cvoCodigo = cvoCodigo;
        this.grpCodigo = grpCodigo;
    }

    public Long getCvoCodigo() {
        return cvoCodigo;
    }

    public void setCvoCodigo(Long cvoCodigo) {
        this.cvoCodigo = cvoCodigo;
    }

    public Short getGrpCodigo() {
        return grpCodigo;
    }

    public void setGrpCodigo(Short grpCodigo) {
        this.grpCodigo = grpCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.cvoCodigo);
        hash = 19 * hash + Objects.hashCode(this.grpCodigo);
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
        final InveConvocatGrupoPK other = (InveConvocatGrupoPK) obj;
        if (!Objects.equals(this.cvoCodigo, other.cvoCodigo)) {
            return false;
        }
        if (!Objects.equals(this.grpCodigo, other.grpCodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveConvocatGrupoPK[ cvoCodigo=" + cvoCodigo + ", grpCodigo=" + grpCodigo + " ]";
    }
    
}
