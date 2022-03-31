/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
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
@Table(name = "PRIN_TIPO_DEDICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinTipoDedicacion.findAll", query = "SELECT p FROM PrinTipoDedicacion p")
    , @NamedQuery(name = "PrinTipoDedicacion.findByTdeCodigo", query = "SELECT p FROM PrinTipoDedicacion p WHERE p.tdeCodigo = :tdeCodigo")
    , @NamedQuery(name = "PrinTipoDedicacion.findByTdeDescripcion", query = "SELECT p FROM PrinTipoDedicacion p WHERE p.tdeDescripcion = :tdeDescripcion")})
public class PrinTipoDedicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TDE_CODIGO")
    private Short tdeCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TDE_DESCRIPCION")
    private String tdeDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinTipoDedicacion")
    private Collection<InveRecursoHumano> inveRecursoHumanoCollection;
    @OneToMany(mappedBy = "prinTipoDedicacion")
    private Collection<InveInformeOper> inveInformeOperCollection;

    public PrinTipoDedicacion() {
    }

    public PrinTipoDedicacion(Short tdeCodigo) {
        this.tdeCodigo = tdeCodigo;
    }

    public PrinTipoDedicacion(Short tdeCodigo, String tdeDescripcion) {
        this.tdeCodigo = tdeCodigo;
        this.tdeDescripcion = tdeDescripcion;
    }

    public Short getTdeCodigo() {
        return tdeCodigo;
    }

    public void setTdeCodigo(Short tdeCodigo) {
        this.tdeCodigo = tdeCodigo;
    }

    public String getTdeDescripcion() {
        return tdeDescripcion;
    }

    public void setTdeDescripcion(String tdeDescripcion) {
        this.tdeDescripcion = tdeDescripcion;
    }

    @XmlTransient
    public Collection<InveRecursoHumano> getInveRecursoHumanoCollection() {
        return inveRecursoHumanoCollection;
    }

    public void setInveRecursoHumanoCollection(Collection<InveRecursoHumano> inveRecursoHumanoCollection) {
        this.inveRecursoHumanoCollection = inveRecursoHumanoCollection;
    }

    @XmlTransient
    public Collection<InveInformeOper> getInveInformeOperCollection() {
        return inveInformeOperCollection;
    }

    public void setInveInformeOperCollection(Collection<InveInformeOper> inveInformeOperCollection) {
        this.inveInformeOperCollection = inveInformeOperCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdeCodigo != null ? tdeCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinTipoDedicacion)) {
            return false;
        }
        PrinTipoDedicacion other = (PrinTipoDedicacion) object;
        if ((this.tdeCodigo == null && other.tdeCodigo != null) || (this.tdeCodigo != null && !this.tdeCodigo.equals(other.tdeCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinTipoDedicacion{" + "tdeCodigo=" + tdeCodigo + ", tdeDescripcion=" + tdeDescripcion + '}';
    }

}
