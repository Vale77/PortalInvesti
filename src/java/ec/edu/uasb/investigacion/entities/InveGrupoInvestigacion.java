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
@Table(name = "INVE_GRUPO_INVESTIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveGrupoInvestigacion.findAll", query = "SELECT i FROM InveGrupoInvestigacion i")
    , @NamedQuery(name = "InveGrupoInvestigacion.findByGinCodigo", query = "SELECT i FROM InveGrupoInvestigacion i WHERE i.ginCodigo = :ginCodigo")})
public class InveGrupoInvestigacion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIN_CODIGO")
    private Short ginCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "GIN_NOMBRE")
    private String ginNombre;
    @OneToMany(mappedBy = "inveGrupoInvestigacion")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveGrupoInvestigacion() {
    }

    public InveGrupoInvestigacion(Short ginCodigo) {
        this.ginCodigo = ginCodigo;
    }

    public InveGrupoInvestigacion(Short ginCodigo, String ginNombre) {
        this.ginCodigo = ginCodigo;
        this.ginNombre = ginNombre;
    }

    public Short getGinCodigo() {
        return ginCodigo;
    }

    public void setGinCodigo(Short ginCodigo) {
        this.ginCodigo = ginCodigo;
    }

    public String getGinNombre() {
        return ginNombre;
    }

    public void setGinNombre(String ginNombre) {
        this.ginNombre = ginNombre;
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
        hash += (ginCodigo != null ? ginCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveGrupoInvestigacion)) {
            return false;
        }
        InveGrupoInvestigacion other = (InveGrupoInvestigacion) object;
        return !((this.ginCodigo == null && other.ginCodigo != null) || (this.ginCodigo != null && !this.ginCodigo.equals(other.ginCodigo)));
    }

    @Override
    public InveGrupoInvestigacion clone() throws CloneNotSupportedException {
        return (InveGrupoInvestigacion) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion[ ginCodigo=" + ginCodigo + " ]";
    }

}
