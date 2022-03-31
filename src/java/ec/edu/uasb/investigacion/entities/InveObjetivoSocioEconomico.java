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
@Table(name = "INVE_OBJETIVO_SOCIO_ECONOMICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveObjetivoSocioEconomico.findAll", query = "SELECT i FROM InveObjetivoSocioEconomico i order by i.oseNombre")
    , @NamedQuery(name = "InveObjetivoSocioEconomico.findByOseCodigo", query = "SELECT i FROM InveObjetivoSocioEconomico i WHERE i.oseCodigo = :oseCodigo")
    , @NamedQuery(name = "InveObjetivoSocioEconomico.findByOseNombre", query = "SELECT i FROM InveObjetivoSocioEconomico i WHERE i.oseNombre = :oseNombre")})
public class InveObjetivoSocioEconomico implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OSE_CODIGO")
    private Short oseCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "OSE_NOMBRE")
    private String oseNombre;
    @OneToMany(mappedBy = "inveObjetivoSocioEconomico")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveObjetivoSocioEconomico() {
    }

    public InveObjetivoSocioEconomico(Short oseCodigo) {
        this.oseCodigo = oseCodigo;
    }

    public InveObjetivoSocioEconomico(Short oseCodigo, String oseNombre) {
        this.oseCodigo = oseCodigo;
        this.oseNombre = oseNombre;
    }

    public Short getOseCodigo() {
        return oseCodigo;
    }

    public void setOseCodigo(Short oseCodigo) {
        this.oseCodigo = oseCodigo;
    }

    public String getOseNombre() {
        return oseNombre;
    }

    public void setOseNombre(String oseNombre) {
        this.oseNombre = oseNombre;
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
        hash += (oseCodigo != null ? oseCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveObjetivoSocioEconomico)) {
            return false;
        }
        InveObjetivoSocioEconomico other = (InveObjetivoSocioEconomico) object;
        if ((this.oseCodigo == null && other.oseCodigo != null) || (this.oseCodigo != null && !this.oseCodigo.equals(other.oseCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveObjetivoSocioEconomico clone() throws CloneNotSupportedException {
        return (InveObjetivoSocioEconomico) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico[ oseCodigo=" + oseCodigo + " ]";
    }
    
}
