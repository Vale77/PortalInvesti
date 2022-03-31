/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

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
@Table(name = "PRIN_TIPO_ENTIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinTipoEntidad.findAll", query = "SELECT p FROM PrinTipoEntidad p")
    , @NamedQuery(name = "PrinTipoEntidad.findByTenCodigo", query = "SELECT p FROM PrinTipoEntidad p WHERE p.tenCodigo = :tenCodigo")
    , @NamedQuery(name = "PrinTipoEntidad.findByTenNombre", query = "SELECT p FROM PrinTipoEntidad p WHERE p.tenNombre = :tenNombre")
    , @NamedQuery(name = "PrinTipoEntidad.findByTenAbrev", query = "SELECT p FROM PrinTipoEntidad p WHERE p.tenAbrev = :tenAbrev")})
public class PrinTipoEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEN_CODIGO")
    private Short tenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TEN_NOMBRE")
    private String tenNombre;
    @Size(max = 10)
    @Column(name = "TEN_ABREV")
    private String tenAbrev;
    @OneToMany(mappedBy = "prinTipoEntidad")
    private Collection<PrinEntidad> prinEntidadCollection;

    public PrinTipoEntidad() {
    }

    public PrinTipoEntidad(Short tenCodigo) {
        this.tenCodigo = tenCodigo;
    }

    public PrinTipoEntidad(Short tenCodigo, String tenNombre) {
        this.tenCodigo = tenCodigo;
        this.tenNombre = tenNombre;
    }

    public Short getTenCodigo() {
        return tenCodigo;
    }

    public void setTenCodigo(Short tenCodigo) {
        this.tenCodigo = tenCodigo;
    }

    public String getTenNombre() {
        return tenNombre;
    }

    public void setTenNombre(String tenNombre) {
        this.tenNombre = tenNombre;
    }

    public String getTenAbrev() {
        return tenAbrev;
    }

    public void setTenAbrev(String tenAbrev) {
        this.tenAbrev = tenAbrev;
    }

    @XmlTransient
    public Collection<PrinEntidad> getPrinEntidadCollection() {
        return prinEntidadCollection;
    }

    public void setPrinEntidadCollection(Collection<PrinEntidad> prinEntidadCollection) {
        this.prinEntidadCollection = prinEntidadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenCodigo != null ? tenCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinTipoEntidad)) {
            return false;
        }
        PrinTipoEntidad other = (PrinTipoEntidad) object;
        if ((this.tenCodigo == null && other.tenCodigo != null) || (this.tenCodigo != null && !this.tenCodigo.equals(other.tenCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinTipoEntidad[ tenCodigo=" + tenCodigo + " ]";
    }
    
}
