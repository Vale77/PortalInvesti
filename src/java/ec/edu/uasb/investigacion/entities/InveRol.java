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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveRol.findAll", query = "SELECT i FROM InveRol i order by i.rolDescripcion")
    , @NamedQuery(name = "InveRol.findByRolCodigo", query = "SELECT i FROM InveRol i WHERE i.rolCodigo = :rolCodigo")
    , @NamedQuery(name = "InveRol.findByRolDescripcion", query = "SELECT i FROM InveRol i WHERE i.rolDescripcion = :rolDescripcion")})
public class InveRol implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_CODIGO")
    private Short rolCodigo;
    @Basic(optional = false)
    @Column(name = "ROL_DESCRIPCION")
    private String rolDescripcion;
    @OneToMany(mappedBy = "inveRol")
    private Collection<InveLector> inveLectorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveRol")
    private Collection<InveEquipoMulti> inveEquipoMultiCollection;
    @OneToMany(mappedBy = "inveRol")
    private Collection<InveEstadoValoracion> inveEstadoValoracionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveRol")
    private Collection<InveRecursoHumano> inveRecursoHumanoCollection;

    public InveRol() {
    }

    public InveRol(Short rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public InveRol(Short rolCodigo, String rolDescripcion) {
        this.rolCodigo = rolCodigo;
        this.rolDescripcion = rolDescripcion;
    }

    public Short getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(Short rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    @XmlTransient
    public Collection<InveLector> getInveLectorCollection() {
        return inveLectorCollection;
    }

    public void setInveLectorCollection(Collection<InveLector> inveLectorCollection) {
        this.inveLectorCollection = inveLectorCollection;
    }

    @XmlTransient
    public Collection<InveEquipoMulti> getInveEquipoMultiCollection() {
        return inveEquipoMultiCollection;
    }

    public void setInveEquipoMultiCollection(Collection<InveEquipoMulti> inveEquipoMultiCollection) {
        this.inveEquipoMultiCollection = inveEquipoMultiCollection;
    }

    @XmlTransient
    public Collection<InveEstadoValoracion> getInveEstadoValoracionCollection() {
        return inveEstadoValoracionCollection;
    }

    public void setInveEstadoValoracionCollection(Collection<InveEstadoValoracion> inveEstadoValoracionCollection) {
        this.inveEstadoValoracionCollection = inveEstadoValoracionCollection;
    }

    @XmlTransient
    public Collection<InveRecursoHumano> getInveRecursoHumanoCollection() {
        return inveRecursoHumanoCollection;
    }

    public void setInveRecursoHumanoCollection(Collection<InveRecursoHumano> inveRecursoHumanoCollection) {
        this.inveRecursoHumanoCollection = inveRecursoHumanoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolCodigo != null ? rolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveRol)) {
            return false;
        }
        InveRol other = (InveRol) object;
        if ((this.rolCodigo == null && other.rolCodigo != null) || (this.rolCodigo != null && !this.rolCodigo.equals(other.rolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveRol clone() throws CloneNotSupportedException {
        return (InveRol) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveRol[ rolCodigo=" + rolCodigo + " ]";
    }

}
