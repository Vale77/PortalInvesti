/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "PRIN_ENTIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinEntidad.findAll", query = "SELECT p FROM PrinEntidad p")
    , @NamedQuery(name = "PrinEntidad.findByEntCodigo", query = "SELECT p FROM PrinEntidad p WHERE p.entCodigo = :entCodigo")
    , @NamedQuery(name = "PrinEntidad.findByEntNombre", query = "SELECT p FROM PrinEntidad p WHERE p.entNombre = :entNombre")})
public class PrinEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENT_CODIGO")
    private Short entCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ENT_NOMBRE")
    private String entNombre;
    @Size(max = 10)
    @Column(name = "ENT_ABREVIATURA")
    private String entAbreviatura;
    @Size(max = 1)
    @Column(name = "PENT_AUTOR")
    private String pentAutor;
    @Size(max = 1)
    @Column(name = "PENT_EDITOR")
    private String pentEditor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ENT_USUARIO")
    private String entUsuario;
    @Column(name = "ENT_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entFechaAct;
    @Size(max = 25)
    @Column(name = "ENT_USUARIO_ACT")
    private String entUsuarioAct;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinEntidad")
    private Collection<PrinTitulo> prinTituloCollection;
    @JoinColumn(name = "TEN_CODIGO", referencedColumnName = "TEN_CODIGO")
    @ManyToOne
    private PrinTipoEntidad prinTipoEntidad;

    public PrinEntidad() {
    }

    public PrinEntidad(Short entCodigo) {
        this.entCodigo = entCodigo;
    }

    public PrinEntidad(Short entCodigo, String entNombre, Date entFechaCrea, String entUsuario) {
        this.entCodigo = entCodigo;
        this.entNombre = entNombre;
        this.entFechaCrea = entFechaCrea;
        this.entUsuario = entUsuario;
    }

    public Short getEntCodigo() {
        return entCodigo;
    }

    public void setEntCodigo(Short entCodigo) {
        this.entCodigo = entCodigo;
    }

    public String getEntNombre() {
        return entNombre;
    }

    public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

    public String getEntAbreviatura() {
        return entAbreviatura;
    }

    public void setEntAbreviatura(String entAbreviatura) {
        this.entAbreviatura = entAbreviatura;
    }

    public String getPentAutor() {
        return pentAutor;
    }

    public void setPentAutor(String pentAutor) {
        this.pentAutor = pentAutor;
    }

    public String getPentEditor() {
        return pentEditor;
    }

    public void setPentEditor(String pentEditor) {
        this.pentEditor = pentEditor;
    }

    public Date getEntFechaCrea() {
        return entFechaCrea;
    }

    public void setEntFechaCrea(Date entFechaCrea) {
        this.entFechaCrea = entFechaCrea;
    }

    public String getEntUsuario() {
        return entUsuario;
    }

    public void setEntUsuario(String entUsuario) {
        this.entUsuario = entUsuario;
    }

    public Date getEntFechaAct() {
        return entFechaAct;
    }

    public void setEntFechaAct(Date entFechaAct) {
        this.entFechaAct = entFechaAct;
    }

    public String getEntUsuarioAct() {
        return entUsuarioAct;
    }

    public void setEntUsuarioAct(String entUsuarioAct) {
        this.entUsuarioAct = entUsuarioAct;
    }

    @XmlTransient
    public Collection<PrinTitulo> getPrinTituloCollection() {
        return prinTituloCollection;
    }

    public void setPrinTituloCollection(Collection<PrinTitulo> prinTituloCollection) {
        this.prinTituloCollection = prinTituloCollection;
    }

    public PrinTipoEntidad getPrinTipoEntidad() {
        return prinTipoEntidad;
    }

    public void setPrinTipoEntidad(PrinTipoEntidad prinTipoEntidad) {
        this.prinTipoEntidad = prinTipoEntidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entCodigo != null ? entCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinEntidad)) {
            return false;
        }
        PrinEntidad other = (PrinEntidad) object;
        if ((this.entCodigo == null && other.entCodigo != null) || (this.entCodigo != null && !this.entCodigo.equals(other.entCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.principal.entities.PrinEntidad[ entCodigo=" + entCodigo + " ]";
    }

}
