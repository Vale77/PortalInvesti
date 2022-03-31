/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class InveRecursoHumanoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_CODIGO")
    private long pryCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PER_CODIGO")
    private long perCodigo;

    public InveRecursoHumanoPK() {
    }

    public InveRecursoHumanoPK(long pryCodigo, long perCodigo) {
        this.pryCodigo = pryCodigo;
        this.perCodigo = perCodigo;
    }

    public long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(long perCodigo) {
        this.perCodigo = perCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pryCodigo;
        hash += (int) perCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveRecursoHumanoPK)) {
            return false;
        }
        InveRecursoHumanoPK other = (InveRecursoHumanoPK) object;
        if (this.pryCodigo != other.pryCodigo) {
            return false;
        }
        if (this.perCodigo != other.perCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveRecursoHumanoPK[ pryCodigo=" + pryCodigo + ", perCodigo=" + perCodigo + " ]";
    }
    
}
