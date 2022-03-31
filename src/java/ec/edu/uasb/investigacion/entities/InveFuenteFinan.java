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
@Table(name = "INVE_FUENTE_FINAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveFuenteFinan.findAll", query = "SELECT i FROM InveFuenteFinan i order by i.finNombre")
    , @NamedQuery(name = "InveFuenteFinan.findByFinCodigo", query = "SELECT i FROM InveFuenteFinan i WHERE i.finCodigo = :finCodigo")
    , @NamedQuery(name = "InveFuenteFinan.findByFinNombre", query = "SELECT i FROM InveFuenteFinan i WHERE i.finNombre = :finNombre")})
public class InveFuenteFinan implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIN_CODIGO")
    private Short finCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "FIN_NOMBRE")
    private String finNombre;
    @OneToMany(mappedBy = "inveFuenteFinan")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveFuenteFinan() {
    }

    public InveFuenteFinan(Short finCodigo) {
        this.finCodigo = finCodigo;
    }

    public InveFuenteFinan(Short finCodigo, String finNombre) {
        this.finCodigo = finCodigo;
        this.finNombre = finNombre;
    }

    public Short getFinCodigo() {
        return finCodigo;
    }

    public void setFinCodigo(Short finCodigo) {
        this.finCodigo = finCodigo;
    }

    public String getFinNombre() {
        return finNombre;
    }

    public void setFinNombre(String finNombre) {
        this.finNombre = finNombre;
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
        hash += (finCodigo != null ? finCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveFuenteFinan)) {
            return false;
        }
        InveFuenteFinan other = (InveFuenteFinan) object;
        if ((this.finCodigo == null && other.finCodigo != null) || (this.finCodigo != null && !this.finCodigo.equals(other.finCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveFuenteFinan clone() throws CloneNotSupportedException {
        return (InveFuenteFinan) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveFuenteFinan[ finCodigo=" + finCodigo + " ]";
    }
    
}
