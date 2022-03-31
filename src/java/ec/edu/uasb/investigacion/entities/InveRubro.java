/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_RUBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveRubro.findAll", query = "SELECT i FROM InveRubro i order by i.rubNombre")
    , @NamedQuery(name = "InveRubro.findByRubCodigo", query = "SELECT i FROM InveRubro i WHERE i.rubCodigo = :rubCodigo")
    , @NamedQuery(name = "InveRubro.findByRubNombre", query = "SELECT i FROM InveRubro i WHERE i.rubNombre = :rubNombre")})
public class InveRubro implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RUB_CODIGO")
    private Short rubCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RUB_NOMBRE")
    private String rubNombre;
    @Size(max = 1000)
    @Column(name = "RUB_DESCRIPCION")
    private String rubDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RUB_MONTO")
    private BigDecimal rubMonto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveRubro")
    private Collection<InvePresupuesto> invePresupuestoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveRubro")
    private Collection<InveRubroGrupo> inveRubroGrupoCollection;
    @Transient
    private String rubMontoEc;
    @Transient
    private String grupos;

    public String getRubMontoEc() {
        Locale locale = new Locale("en", "US");
        String pattern = "#,###.00";

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        String format = decimalFormat.format(this.getRubMonto());
        return format;
    }

    public void setRubMontoEc(String rubMontoEc) {
        this.rubMontoEc = rubMontoEc;
    }

    public InveRubro() {
    }

    public InveRubro(Short rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    public InveRubro(Short rubCodigo, String rubNombre) {
        this.rubCodigo = rubCodigo;
        this.rubNombre = rubNombre;
    }

    public Short getRubCodigo() {
        return rubCodigo;
    }

    public void setRubCodigo(Short rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    public String getRubNombre() {
        return rubNombre;
    }

    public void setRubNombre(String rubNombre) {
        this.rubNombre = rubNombre;
    }

    public String getRubDescripcion() {
        return rubDescripcion;
    }

    public void setRubDescripcion(String rubDescripcion) {
        this.rubDescripcion = rubDescripcion;
    }

    public BigDecimal getRubMonto() {
        return rubMonto;
    }

    public void setRubMonto(BigDecimal rubMonto) {
        this.rubMonto = rubMonto;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    @XmlTransient
    public Collection<InvePresupuesto> getInvePresupuestoCollection() {
        return invePresupuestoCollection;
    }

    public void setInvePresupuestoCollection(Collection<InvePresupuesto> invePresupuestoCollection) {
        this.invePresupuestoCollection = invePresupuestoCollection;
    }

    @XmlTransient
    public Collection<InveRubroGrupo> getInveRubroGrupoCollection() {
        return inveRubroGrupoCollection;
    }

    public void setInveRubroGrupoCollection(Collection<InveRubroGrupo> inveRubroGrupoCollection) {
        this.inveRubroGrupoCollection = inveRubroGrupoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubCodigo != null ? rubCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveRubro)) {
            return false;
        }
        InveRubro other = (InveRubro) object;
        if ((this.rubCodigo == null && other.rubCodigo != null) || (this.rubCodigo != null && !this.rubCodigo.equals(other.rubCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveRubro clone() throws CloneNotSupportedException {
        return (InveRubro) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveRubro[ rubCodigo=" + rubCodigo + " ]";
    }

}
