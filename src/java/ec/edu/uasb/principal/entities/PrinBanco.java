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
@Table(name = "PRIN_BANCO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinBanco.findAll", query = "SELECT p FROM PrinBanco p")
    , @NamedQuery(name = "PrinBanco.findByBanCodigo", query = "SELECT p FROM PrinBanco p WHERE p.banCodigo = :banCodigo")
    , @NamedQuery(name = "PrinBanco.findByBanNombre", query = "SELECT p FROM PrinBanco p WHERE p.banNombre = :banNombre")})
public class PrinBanco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BAN_CODIGO")
    private Short banCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BAN_NOMBRE")
    private String banNombre;
    @OneToMany(mappedBy = "prinBanco")
    private Collection<PrinPersona> prinPersonaCollection;

    public PrinBanco() {
    }

    public PrinBanco(Short banCodigo) {
        this.banCodigo = banCodigo;
    }

    public PrinBanco(Short banCodigo, String banNombre) {
        this.banCodigo = banCodigo;
        this.banNombre = banNombre;
    }

    public Short getBanCodigo() {
        return banCodigo;
    }

    public void setBanCodigo(Short banCodigo) {
        this.banCodigo = banCodigo;
    }

    public String getBanNombre() {
        return banNombre;
    }

    public void setBanNombre(String banNombre) {
        this.banNombre = banNombre;
    }

    @XmlTransient
    public Collection<PrinPersona> getPrinPersonaCollection() {
        return prinPersonaCollection;
    }

    public void setPrinPersonaCollection(Collection<PrinPersona> prinPersonaCollection) {
        this.prinPersonaCollection = prinPersonaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (banCodigo != null ? banCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinBanco)) {
            return false;
        }
        PrinBanco other = (PrinBanco) object;
        if ((this.banCodigo == null && other.banCodigo != null) || (this.banCodigo != null && !this.banCodigo.equals(other.banCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinBanco[ banCodigo=" + banCodigo + " ]";
    }
    
}
