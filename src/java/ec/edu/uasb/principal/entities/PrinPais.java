/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "PRIN_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinPais.findAll", query = "SELECT p FROM PrinPais p")
    , @NamedQuery(name = "PrinPais.findByPaiCodigo", query = "SELECT p FROM PrinPais p WHERE p.paiCodigo = :paiCodigo")
    , @NamedQuery(name = "PrinPais.findByPaiNombre", query = "SELECT p FROM PrinPais p WHERE p.paiNombre = :paiNombre")})
public class PrinPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAI_CODIGO")
    private Short paiCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "PAI_NOMBRE")
    private String paiNombre;
    @Size(max = 5)
    @Column(name = "PAI_COD_INTER")
    private String paiCodInter;
    @Size(max = 25)
    @Column(name = "PAI_VOC_NACIONALIDAD")
    private String paiVocNacionalidad;
    @Size(max = 5)
    @Column(name = "PAI_ABREV")
    private String paiAbrev;
    @Column(name = "PAI_CODIGO_SNIESE")
    private Short paiCodigoSniese;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinPais")
    private Collection<PrinTitulo> prinTituloCollection;
    @OneToMany(mappedBy = "prinNacionalidad")
    private Collection<PrinPersona> prinPersonaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinPais")
    private Collection<PrinCiudad> prinCiudadCollection;
    @OneToMany(mappedBy = "prinPais")
    private Collection<InveProyecto> inveProyectoCollection;

    public PrinPais() {
    }

    public PrinPais(Short paiCodigo) {
        this.paiCodigo = paiCodigo;
    }

    public PrinPais(Short paiCodigo, String paiNombre) {
        this.paiCodigo = paiCodigo;
        this.paiNombre = paiNombre;
    }

    public Short getPaiCodigo() {
        return paiCodigo;
    }

    public void setPaiCodigo(Short paiCodigo) {
        this.paiCodigo = paiCodigo;
    }

    public String getPaiNombre() {
        return paiNombre;
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre = paiNombre;
    }

    public String getPaiCodInter() {
        return paiCodInter;
    }

    public void setPaiCodInter(String paiCodInter) {
        this.paiCodInter = paiCodInter;
    }

    public String getPaiVocNacionalidad() {
        return paiVocNacionalidad;
    }

    public void setPaiVocNacionalidad(String paiVocNacionalidad) {
        this.paiVocNacionalidad = paiVocNacionalidad;
    }

    public String getPaiAbrev() {
        return paiAbrev;
    }

    public void setPaiAbrev(String paiAbrev) {
        this.paiAbrev = paiAbrev;
    }

    public Short getPaiCodigoSniese() {
        return paiCodigoSniese;
    }

    public void setPaiCodigoSniese(Short paiCodigoSniese) {
        this.paiCodigoSniese = paiCodigoSniese;
    }

    @XmlTransient
    public Collection<PrinTitulo> getPrinTituloCollection() {
        return prinTituloCollection;
    }

    public void setPrinTituloCollection(Collection<PrinTitulo> prinTituloCollection) {
        this.prinTituloCollection = prinTituloCollection;
    }

    @XmlTransient
    public Collection<PrinPersona> getPrinPersonaCollection() {
        return prinPersonaCollection;
    }

    public void setPrinPersonaCollection(Collection<PrinPersona> prinPersonaCollection) {
        this.prinPersonaCollection = prinPersonaCollection;
    }

    @XmlTransient
    public Collection<PrinCiudad> getPrinCiudadCollection() {
        return prinCiudadCollection;
    }

    public void setPrinCiudadCollection(Collection<PrinCiudad> prinCiudadCollection) {
        this.prinCiudadCollection = prinCiudadCollection;
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
        hash += (paiCodigo != null ? paiCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinPais)) {
            return false;
        }
        PrinPais other = (PrinPais) object;
        if ((this.paiCodigo == null && other.paiCodigo != null) || (this.paiCodigo != null && !this.paiCodigo.equals(other.paiCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinPais[ paiCodigo=" + paiCodigo + " ]";
    }

}
