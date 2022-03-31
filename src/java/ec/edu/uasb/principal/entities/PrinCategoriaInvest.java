/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "PRIN_CATEGORIA_INVEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinCategoriaInvest.findAll", query = "SELECT p FROM PrinCategoriaInvest p")
    , @NamedQuery(name = "PrinCategoriaInvest.findByCaiCodigo", query = "SELECT p FROM PrinCategoriaInvest p WHERE p.caiCodigo = :caiCodigo")})
public class PrinCategoriaInvest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAI_CODIGO")
    private Short caiCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CAI_DESCRIPCION")
    private String caiDescripcion;
    @OneToMany(mappedBy = "prinCategoriaInvest")
    private Collection<InveProyecto> inveProyectoCollection;

    public PrinCategoriaInvest() {
    }

    public PrinCategoriaInvest(Short caiCodigo) {
        this.caiCodigo = caiCodigo;
    }

    public PrinCategoriaInvest(Short caiCodigo, String caiDescripcion) {
        this.caiCodigo = caiCodigo;
        this.caiDescripcion = caiDescripcion;
    }

    public Short getCaiCodigo() {
        return caiCodigo;
    }

    public void setCaiCodigo(Short caiCodigo) {
        this.caiCodigo = caiCodigo;
    }

    public String getCaiDescripcion() {
        return caiDescripcion;
    }

    public void setCaiDescripcion(String caiDescripcion) {
        this.caiDescripcion = caiDescripcion;
    }

    @XmlTransient
    public Collection<InveProyecto> getInveProyectoCollection() {
        return inveProyectoCollection;
    }

    public void setInveProyectoCollection(Collection<InveProyecto> inveProyectoCollection) {
        this.inveProyectoCollection = inveProyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caiCodigo != null ? caiCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinCategoriaInvest)) {
            return false;
        }
        PrinCategoriaInvest other = (PrinCategoriaInvest) object;
        if ((this.caiCodigo == null && other.caiCodigo != null) || (this.caiCodigo != null && !this.caiCodigo.equals(other.caiCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinCategoriaInvest{" + "caiCodigo=" + caiCodigo + ", caiDescripcion=" + caiDescripcion + '}';
    }

}
