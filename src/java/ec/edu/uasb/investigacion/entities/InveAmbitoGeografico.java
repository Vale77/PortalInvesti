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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "INVE_AMBITO_GEOGRAFICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveAmbitoGeografico.findAll", query = "SELECT i FROM InveAmbitoGeografico i ORDER BY i.ambNombre")
    , @NamedQuery(name = "InveAmbitoGeografico.findByAmbCodigo", query = "SELECT i FROM InveAmbitoGeografico i WHERE i.ambCodigo = :ambCodigo")
    , @NamedQuery(name = "InveAmbitoGeografico.findByAmbNombre", query = "SELECT i FROM InveAmbitoGeografico i WHERE i.ambNombre = :ambNombre")})
public class InveAmbitoGeografico implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AMB_CODIGO")
    private Short ambCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "AMB_NOMBRE")
    private String ambNombre;
    @OneToMany(mappedBy = "inveAmbitoGeografico")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveAmbitoGeografico() {
    }

    public InveAmbitoGeografico(Short ambCodigo) {
        this.ambCodigo = ambCodigo;
    }

    public InveAmbitoGeografico(Short ambCodigo, String ambNombre) {
        this.ambCodigo = ambCodigo;
        this.ambNombre = ambNombre;
    }

    public Short getAmbCodigo() {
        return ambCodigo;
    }

    public void setAmbCodigo(Short ambCodigo) {
        this.ambCodigo = ambCodigo;
    }

    public String getAmbNombre() {
        return ambNombre;
    }

    public void setAmbNombre(String ambNombre) {
        this.ambNombre = ambNombre;
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
        hash += (ambCodigo != null ? ambCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveAmbitoGeografico)) {
            return false;
        }
        InveAmbitoGeografico other = (InveAmbitoGeografico) object;
        return !((this.ambCodigo == null && other.ambCodigo != null) || (this.ambCodigo != null && !this.ambCodigo.equals(other.ambCodigo)));
    }

    @Override
    public InveAmbitoGeografico clone() throws CloneNotSupportedException {
        return (InveAmbitoGeografico) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveAmbitoGeografico[ ambCodigo=" + ambCodigo + " ]";
    }

}
