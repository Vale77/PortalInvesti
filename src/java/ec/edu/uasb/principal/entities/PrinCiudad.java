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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "PRIN_CIUDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinCiudad.findAll", query = "SELECT p FROM PrinCiudad p")
    , @NamedQuery(name = "PrinCiudad.findByPaiCodigo", query = "SELECT p FROM PrinCiudad p WHERE p.prinCiudadPK.paiCodigo = :paiCodigo")
    , @NamedQuery(name = "PrinCiudad.findByCiuCodigo", query = "SELECT p FROM PrinCiudad p WHERE p.prinCiudadPK.ciuCodigo = :ciuCodigo")
    , @NamedQuery(name = "PrinCiudad.findByCiuNombre", query = "SELECT p FROM PrinCiudad p WHERE p.ciuNombre = :ciuNombre")})
public class PrinCiudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrinCiudadPK prinCiudadPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CIU_NOMBRE")
    private String ciuNombre;
    @OneToMany(mappedBy = "prinCiudad")
    private Collection<PrinPersona> prinPersonaCollection;
    @JoinColumn(name = "PAI_CODIGO", referencedColumnName = "PAI_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrinPais prinPais;

    public PrinCiudad() {
    }

    public PrinCiudad(PrinCiudadPK prinCiudadPK) {
        this.prinCiudadPK = prinCiudadPK;
    }

    public PrinCiudad(PrinCiudadPK prinCiudadPK, String ciuNombre) {
        this.prinCiudadPK = prinCiudadPK;
        this.ciuNombre = ciuNombre;
    }

    public PrinCiudad(Short paiCodigo, Integer ciuCodigo) {
        this.prinCiudadPK = new PrinCiudadPK(paiCodigo, ciuCodigo);
    }

    public PrinCiudadPK getPrinCiudadPK() {
        return prinCiudadPK;
    }

    public void setPrinCiudadPK(PrinCiudadPK prinCiudadPK) {
        this.prinCiudadPK = prinCiudadPK;
    }

    public String getCiuNombre() {
        return ciuNombre;
    }

    public void setCiuNombre(String ciuNombre) {
        this.ciuNombre = ciuNombre;
    }

    @XmlTransient
    public Collection<PrinPersona> getPrinPersonaCollection() {
        return prinPersonaCollection;
    }

    public void setPrinPersonaCollection(Collection<PrinPersona> prinPersonaCollection) {
        this.prinPersonaCollection = prinPersonaCollection;
    }

    public PrinPais getPrinPais() {
        return prinPais;
    }

    public void setPrinPais(PrinPais prinPais) {
        this.prinPais = prinPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prinCiudadPK != null ? prinCiudadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinCiudad)) {
            return false;
        }
        PrinCiudad other = (PrinCiudad) object;
        if ((this.prinCiudadPK == null && other.prinCiudadPK != null) || (this.prinCiudadPK != null && !this.prinCiudadPK.equals(other.prinCiudadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinCiudad[ prinCiudadPK=" + prinCiudadPK + " ]";
    }

}
