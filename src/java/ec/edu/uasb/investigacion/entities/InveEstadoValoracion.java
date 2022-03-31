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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_ESTADO_VALORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveEstadoValoracion.findAll", query = "SELECT i FROM InveEstadoValoracion i")
    , @NamedQuery(name = "InveEstadoValoracion.findByEsvCodigo", query = "SELECT i FROM InveEstadoValoracion i WHERE i.esvCodigo = :esvCodigo")})
public class InveEstadoValoracion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ESV_CODIGO")
    private String esvCodigo;
    @Column(name = "ESV_ESTADO")
    private String esvEstado;
    @Basic(optional = false)
    @Column(name = "ESV_DESCRIPCION")
    private String esvDescripcion;
    @OneToMany(mappedBy = "inveEstadoValoracion")
    private Collection<InveValoracion> inveValoracionCollection;
    @JoinColumn(name = "ROL_CODIGO", referencedColumnName = "ROL_CODIGO")
    @ManyToOne
    private InveRol inveRol;

    public InveEstadoValoracion() {
    }

    public InveEstadoValoracion(String esvCodigo) {
        this.esvCodigo = esvCodigo;
    }

    public InveEstadoValoracion(String esvCodigo, String esvDescripcion) {
        this.esvCodigo = esvCodigo;
        this.esvDescripcion = esvDescripcion;
    }

    public String getEsvCodigo() {
        return esvCodigo;
    }

    public void setEsvCodigo(String esvCodigo) {
        this.esvCodigo = esvCodigo;
    }

    public String getEsvEstado() {
        return esvEstado;
    }

    public void setEsvEstado(String esvEstado) {
        this.esvEstado = esvEstado;
    }

    public String getEsvDescripcion() {
        return esvDescripcion;
    }

    public void setEsvDescripcion(String esvDescripcion) {
        this.esvDescripcion = esvDescripcion;
    }

    public InveRol getInveRol() {
        return inveRol;
    }

    public void setInveRol(InveRol inveRol) {
        this.inveRol = inveRol;
    }

    @XmlTransient
    public Collection<InveValoracion> getInveValoracionCollection() {
        return inveValoracionCollection;
    }

    public void setInveValoracionCollection(Collection<InveValoracion> inveValoracionCollection) {
        this.inveValoracionCollection = inveValoracionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esvCodigo != null ? esvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveEstadoValoracion)) {
            return false;
        }
        InveEstadoValoracion other = (InveEstadoValoracion) object;
        if ((this.esvCodigo == null && other.esvCodigo != null) || (this.esvCodigo != null && !this.esvCodigo.equals(other.esvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveEstadoValoracion clone() throws CloneNotSupportedException {
        return (InveEstadoValoracion) super.clone();
    }

    @Override
    public String toString() {
        return esvEstado + " - " + esvDescripcion;
    }

}
