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
@Table(name = "INVE_GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveGrupo.findAll", query = "SELECT i FROM InveGrupo i order by i.grpNombre")
    , @NamedQuery(name = "InveGrupo.findByGrpCodigo", query = "SELECT i FROM InveGrupo i WHERE i.grpCodigo = :grpCodigo")})
public class InveGrupo implements Serializable, Cloneable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveGrupo")
    private Collection<InveConvocatGrupo> inveConvocatGrupoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRP_CODIGO")
    private Short grpCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GRP_NOMBRE")
    private String grpNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveGrupo")
    private Collection<InveRubroGrupo> inveRubroGrupoCollection;
    
    public InveGrupo() {
    }

    public InveGrupo(Short grpCodigo) {
        this.grpCodigo = grpCodigo;
    }

    public InveGrupo(Short grpCodigo, String grpNombre) {
        this.grpCodigo = grpCodigo;
        this.grpNombre = grpNombre;
    }

    public Short getGrpCodigo() {
        return grpCodigo;
    }

    public void setGrpCodigo(Short grpCodigo) {
        this.grpCodigo = grpCodigo;
    }

    public String getGrpNombre() {
        return grpNombre;
    }

    public void setGrpNombre(String grpNombre) {
        this.grpNombre = grpNombre;
    }

    @XmlTransient
    public Collection<InveConvocatGrupo> getInveConvocatGrupoCollection() {
        return inveConvocatGrupoCollection;
    }

    public void setInveConvocatGrupoCollection(Collection<InveConvocatGrupo> inveConvocatGrupoCollection) {
        this.inveConvocatGrupoCollection = inveConvocatGrupoCollection;
    }
    @XmlTransient
    public Collection<InveRubroGrupo> getInveRubroGrupoCollection() {
        return inveRubroGrupoCollection;
    }

    public void setInveRubroGrupoCollection(Collection<InveRubroGrupo> inveRubroGrupoCollection) {
        this.inveRubroGrupoCollection = inveRubroGrupoCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grpCodigo != null ? grpCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveGrupo)) {
            return false;
        }
        InveGrupo other = (InveGrupo) object;
        if ((this.grpCodigo == null && other.grpCodigo != null) || (this.grpCodigo != null && !this.grpCodigo.equals(other.grpCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveGrupo clone() throws CloneNotSupportedException {
        return (InveGrupo) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveGrupo[ grpCodigo=" + grpCodigo + " ]";
    }

}
