/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.vinculacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "VINC_CINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VincCine.findAll", query = "SELECT v FROM VincCine v")
    , @NamedQuery(name = "VincCine.findByCinCodigo", query = "SELECT v FROM VincCine v WHERE v.cinCodigo = :cinCodigo")
    , @NamedQuery(name = "VincCine.findByCinCampo", query = "SELECT v FROM VincCine v WHERE v.cinCampo = :cinCampo")})
public class VincCine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CIN_CODIGO")
    private String cinCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CIN_CAMPO")
    private String cinCampo;
    @OneToMany(mappedBy = "vincCine")
    private Collection<VincCine> vincCineCollection;
    @JoinColumn(name = "CIN_CIN_CODIGO", referencedColumnName = "CIN_CODIGO")
    @ManyToOne
    private VincCine vincCine;
    @OneToMany(mappedBy = "vincCine")
    private Collection<InveProyecto> inveProyectoCollection;

    public VincCine() {
    }

    public VincCine(String cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public VincCine(String cinCodigo, String cinCampo) {
        this.cinCodigo = cinCodigo;
        this.cinCampo = cinCampo;
    }

    public String getCinCodigo() {
        return cinCodigo;
    }

    public void setCinCodigo(String cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public String getCinCampo() {
        return cinCampo;
    }

    public void setCinCampo(String cinCampo) {
        this.cinCampo = cinCampo;
    }

    @XmlTransient
    public Collection<VincCine> getVincCineCollection() {
        return vincCineCollection;
    }

    public void setVincCineCollection(Collection<VincCine> vincCineCollection) {
        this.vincCineCollection = vincCineCollection;
    }

    public VincCine getVincCine() {
        return vincCine;
    }

    public void setVincCine(VincCine vincCine) {
        this.vincCine = vincCine;
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
        hash += (cinCodigo != null ? cinCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VincCine)) {
            return false;
        }
        VincCine other = (VincCine) object;
        if ((this.cinCodigo == null && other.cinCodigo != null) || (this.cinCodigo != null && !this.cinCodigo.equals(other.cinCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.VincCine[ cinCodigo=" + cinCodigo + " ]";
    }

}
