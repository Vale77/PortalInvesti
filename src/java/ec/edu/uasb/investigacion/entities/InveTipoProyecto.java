/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

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
@Table(name = "INVE_TIPO_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveTipoProyecto.findAll", query = "SELECT i FROM InveTipoProyecto i")
    , @NamedQuery(name = "InveTipoProyecto.findByTipCodigo", query = "SELECT i FROM InveTipoProyecto i WHERE i.tipCodigo = :tipCodigo")
    , @NamedQuery(name = "InveTipoProyecto.findByTipNombre", query = "SELECT i FROM InveTipoProyecto i WHERE i.tipNombre = :tipNombre")})
public class InveTipoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_CODIGO")
    private Short tipCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TIP_NOMBRE")
    private String tipNombre;
    @OneToMany(mappedBy = "inveTipoProyecto")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveTipoProyecto() {
    }

    public InveTipoProyecto(Short tipCodigo) {
        this.tipCodigo = tipCodigo;
    }

    public InveTipoProyecto(Short tipCodigo, String tipNombre) {
        this.tipCodigo = tipCodigo;
        this.tipNombre = tipNombre;
    }

    public Short getTipCodigo() {
        return tipCodigo;
    }

    public void setTipCodigo(Short tipCodigo) {
        this.tipCodigo = tipCodigo;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
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
        hash += (tipCodigo != null ? tipCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveTipoProyecto)) {
            return false;
        }
        InveTipoProyecto other = (InveTipoProyecto) object;
        if ((this.tipCodigo == null && other.tipCodigo != null) || (this.tipCodigo != null && !this.tipCodigo.equals(other.tipCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveTipoProyecto[ tipCodigo=" + tipCodigo + " ]";
    }
    
}
