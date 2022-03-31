/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "PRIN_TITULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinTitulo.findAll", query = "SELECT p FROM PrinTitulo p")
    , @NamedQuery(name = "PrinTitulo.findByTitCodigo", query = "SELECT p FROM PrinTitulo p WHERE p.titCodigo = :titCodigo")
    , @NamedQuery(name = "PrinTitulo.findByTitulo", query = "SELECT p FROM PrinTitulo p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "PrinTitulo.findByTitNivelAcad", query = "SELECT p FROM PrinTitulo p WHERE p.titNivelAcad = :titNivelAcad")
    , @NamedQuery(name = "PrinTitulo.findByEsPrincipal", query = "SELECT p FROM PrinTitulo p WHERE p.esPrincipal = :esPrincipal")})
public class PrinTitulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIT_CODIGO")
    private Short titCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 25)
    @Column(name = "TIT_NIVEL_ACAD")
    private String titNivelAcad;
    @Size(max = 1)
    @Column(name = "ES_PRINCIPAL")
    private String esPrincipal;
    @Size(max = 30)
    @Column(name = "NRO_REGISTRO")
    private String nroRegistro;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "ENT_CODIGO", referencedColumnName = "ENT_CODIGO")
    @ManyToOne(optional = false)
    private PrinEntidad prinEntidad;
    @JoinColumn(name = "PAI_CODIGO", referencedColumnName = "PAI_CODIGO")
    @ManyToOne(optional = false)
    private PrinPais prinPais;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
    @ManyToOne(optional = false)
    private PrinPersona prinPersona;

    public PrinTitulo() {
    }

    public PrinTitulo(Short titCodigo) {
        this.titCodigo = titCodigo;
    }

    public PrinTitulo(Short titCodigo, String titulo) {
        this.titCodigo = titCodigo;
        this.titulo = titulo;
    }

    public Short getTitCodigo() {
        return titCodigo;
    }

    public void setTitCodigo(Short titCodigo) {
        this.titCodigo = titCodigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitNivelAcad() {
        return titNivelAcad;
    }

    public void setTitNivelAcad(String titNivelAcad) {
        this.titNivelAcad = titNivelAcad;
    }

    public String getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(String esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public PrinEntidad getPrinEntidad() {
        return prinEntidad;
    }

    public void setPrinEntidad(PrinEntidad prinEntidad) {
        this.prinEntidad = prinEntidad;
    }

    public PrinPais getPrinPais() {
        return prinPais;
    }

    public void setPrinPais(PrinPais prinPais) {
        this.prinPais = prinPais;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titCodigo != null ? titCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinTitulo)) {
            return false;
        }
        PrinTitulo other = (PrinTitulo) object;
        if ((this.titCodigo == null && other.titCodigo != null) || (this.titCodigo != null && !this.titCodigo.equals(other.titCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinTitulo{" + "titCodigo=" + titCodigo
                + ", titulo=" + titulo
                + ", titNivelAcad=" + titNivelAcad
                + ", esPrincipal=" + esPrincipal
                + ", nroRegistro=" + nroRegistro
                + ", fechaRegistro=" + fechaRegistro
                + ", prinEntidad=" + prinEntidad
                + ", prinPais=" + prinPais
                + ", prinPersona=" + prinPersona
                + '}';
    }

}
