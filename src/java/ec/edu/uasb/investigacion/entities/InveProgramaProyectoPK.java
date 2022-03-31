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

/**
 *
 * @author victor.barba
 */
@Embeddable
public class InveProgramaProyectoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PRY_CODIGO")
    private long pryCodigo;
    @Basic(optional = false)
    @Column(name = "PRG_CODIGO")
    private long prgCodigo;

    public InveProgramaProyectoPK() {
    }

    public InveProgramaProyectoPK(long pryCodigo, long prgCodigo) {
        this.pryCodigo = pryCodigo;
        this.prgCodigo = prgCodigo;
    }

    public long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public long getPrgCodigo() {
        return prgCodigo;
    }

    public void setPrgCodigo(long prgCodigo) {
        this.prgCodigo = prgCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pryCodigo;
        hash += (int) prgCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveProgramaProyectoPK)) {
            return false;
        }
        InveProgramaProyectoPK other = (InveProgramaProyectoPK) object;
        if (this.pryCodigo != other.pryCodigo) {
            return false;
        }
        return this.prgCodigo == other.prgCodigo;
    }

    @Override
    public String toString() {
        return "javaapplication10.InveProgramaProyectoPK[ pryCodigo=" + pryCodigo + ", prgCodigo=" + prgCodigo + " ]";
    }
    
}
