/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

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
@Table(name = "PRIN_CLASE_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinClaseDocumento.findAll", query = "SELECT p FROM PrinClaseDocumento p")
    , @NamedQuery(name = "PrinClaseDocumento.findByClsDocumento", query = "SELECT p FROM PrinClaseDocumento p WHERE p.clsDocumento = :clsDocumento")
    , @NamedQuery(name = "PrinClaseDocumento.findByClsModulo", query = "SELECT p FROM PrinClaseDocumento p WHERE p.clsModulo = :clsModulo or p.clsModulo is null")})
public class PrinClaseDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLS_CODIGO")
    private Short clsCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CLS_DOCUMENTO")
    private String clsDocumento;
    @Basic(optional = false)
    @Column(name = "CLS_MODULO")
    private String clsModulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinClaseDocumento")
    private Collection<PrinDocumento> prinDocumentoCollection;

    public PrinClaseDocumento() {
    }

    public PrinClaseDocumento(Short clsCodigo) {
        this.clsCodigo = clsCodigo;
    }

    public PrinClaseDocumento(Short clsCodigo, String clsDocumento, String clsModulo) {
        this.clsCodigo = clsCodigo;
        this.clsDocumento = clsDocumento;
        this.clsModulo = clsModulo;
    }

    public Short getClsCodigo() {
        return clsCodigo;
    }

    public void setClsCodigo(Short clsCodigo) {
        this.clsCodigo = clsCodigo;
    }

    public String getClsDocumento() {
        return clsDocumento;
    }

    public void setClsDocumento(String clsDocumento) {
        this.clsDocumento = clsDocumento;
    }

    public String getClsModulo() {
        return clsModulo;
    }

    public void setClsModulo(String clsModulo) {
        this.clsModulo = clsModulo;
    }

    @XmlTransient
    public Collection<PrinDocumento> getPrinDocumentoCollection() {
        return prinDocumentoCollection;
    }

    public void setPrinDocumentoCollection(Collection<PrinDocumento> prinDocumentoCollection) {
        this.prinDocumentoCollection = prinDocumentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clsCodigo != null ? clsCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinClaseDocumento)) {
            return false;
        }
        PrinClaseDocumento other = (PrinClaseDocumento) object;
        if ((this.clsCodigo == null && other.clsCodigo != null) || (this.clsCodigo != null && !this.clsCodigo.equals(other.clsCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinClaseDocumento{" + "clsCodigo=" + clsCodigo + ", clsDocumento=" + clsDocumento + ", clsModulo=" + clsModulo + '}';
    }

}
