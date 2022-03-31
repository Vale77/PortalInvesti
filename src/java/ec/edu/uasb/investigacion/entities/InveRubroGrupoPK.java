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
public class InveRubroGrupoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "GRP_CODIGO")
    private Short grpCodigo;
    @Basic(optional = false)
    @Column(name = "RUB_CODIGO")
    private Short rubCodigo;

    public InveRubroGrupoPK() {
    }

    public InveRubroGrupoPK(Short grpCodigo, Short rubCodigo) {
        this.grpCodigo = grpCodigo;
        this.rubCodigo = rubCodigo;
    }

    public Short getGrpCodigo() {
        return grpCodigo;
    }

    public void setGrpCodigo(Short grpCodigo) {
        this.grpCodigo = grpCodigo;
    }

    public Short getRubCodigo() {
        return rubCodigo;
    }

    public void setRubCodigo(Short rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.grpCodigo);
        hash = 67 * hash + Objects.hashCode(this.rubCodigo);
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
        final InveRubroGrupoPK other = (InveRubroGrupoPK) obj;
        if (!Objects.equals(this.grpCodigo, other.grpCodigo)) {
            return false;
        }
        return Objects.equals(this.rubCodigo, other.rubCodigo);
    }


    @Override
    public String toString() {
        return "javaapplication2.InveRubroGrupoPK[ grpCodigo=" + grpCodigo + ", rubCodigo=" + rubCodigo + " ]";
    }
    
}
