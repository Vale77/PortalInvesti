/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "INVE_TIPO_SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveTipoSeguimiento.findAll", query = "SELECT i FROM InveTipoSeguimiento i")
    , @NamedQuery(name = "InveTipoSeguimiento.findByTseCodigo", query = "SELECT i FROM InveTipoSeguimiento i WHERE i.tseCodigo = :tseCodigo")
    , @NamedQuery(name = "InveTipoSeguimiento.findByTseNombre", query = "SELECT i FROM InveTipoSeguimiento i WHERE i.tseNombre = :tseNombre")
    , @NamedQuery(name = "InveTipoSeguimiento.findByTseOrden", query = "SELECT i FROM InveTipoSeguimiento i WHERE i.tseOrden = :tseOrden")})
public class InveTipoSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSE_CODIGO")
    private Short tseCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TSE_NOMBRE")
    private String tseNombre;
    @Column(name = "TSE_ORDEN")
    private Short tseOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveTipoSeguimiento")
    private Collection<InveSeguimiento> inveSeguimientoCollection;

    public InveTipoSeguimiento() {
    }

    public InveTipoSeguimiento(Short tseCodigo) {
        this.tseCodigo = tseCodigo;
    }

    public InveTipoSeguimiento(Short tseCodigo, String tseNombre) {
        this.tseCodigo = tseCodigo;
        this.tseNombre = tseNombre;
    }

    public Short getTseCodigo() {
        return tseCodigo;
    }

    public void setTseCodigo(Short tseCodigo) {
        this.tseCodigo = tseCodigo;
    }

    public String getTseNombre() {
        return tseNombre;
    }

    public void setTseNombre(String tseNombre) {
        this.tseNombre = tseNombre;
    }

    public Short getTseOrden() {
        return tseOrden;
    }

    public void setTseOrden(Short tseOrden) {
        this.tseOrden = tseOrden;
    }

    @XmlTransient
    public Collection<InveSeguimiento> getInveSeguimientoCollection() {
        return inveSeguimientoCollection;
    }

    public void setInveSeguimientoCollection(Collection<InveSeguimiento> inveSeguimientoCollection) {
        this.inveSeguimientoCollection = inveSeguimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tseCodigo != null ? tseCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveTipoSeguimiento)) {
            return false;
        }
        InveTipoSeguimiento other = (InveTipoSeguimiento) object;
        if ((this.tseCodigo == null && other.tseCodigo != null) || (this.tseCodigo != null && !this.tseCodigo.equals(other.tseCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveTipoSeguimiento[ tseCodigo=" + tseCodigo + " ]";
    }

}
