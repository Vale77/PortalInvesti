/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "INVE_DISCIPLINA_CIENTIFICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveDisciplinaCientifica.findAll", query = "SELECT i FROM InveDisciplinaCientifica i order by i.dscNombre")
    , @NamedQuery(name = "InveDisciplinaCientifica.findByDscCodigo", query = "SELECT i FROM InveDisciplinaCientifica i WHERE i.dscCodigo = :dscCodigo")
    , @NamedQuery(name = "InveDisciplinaCientifica.findByDscNombre", query = "SELECT i FROM InveDisciplinaCientifica i WHERE i.dscNombre = :dscNombre")})
public class InveDisciplinaCientifica implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DSC_CODIGO")
    private Short dscCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DSC_NOMBRE")
    private String dscNombre;
    @OneToMany(mappedBy = "inveDisciplinaCientifica")
    private Collection<InveProyecto> inveProyectoCollection;

    public InveDisciplinaCientifica() {
    }

    public InveDisciplinaCientifica(Short dscCodigo) {
        this.dscCodigo = dscCodigo;
    }

    public InveDisciplinaCientifica(Short dscCodigo, String dscNombre) {
        this.dscCodigo = dscCodigo;
        this.dscNombre = dscNombre;
    }

    public Short getDscCodigo() {
        return dscCodigo;
    }

    public void setDscCodigo(Short dscCodigo) {
        this.dscCodigo = dscCodigo;
    }

    public String getDscNombre() {
        return dscNombre;
    }

    public void setDscNombre(String dscNombre) {
        this.dscNombre = dscNombre;
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
        hash += (dscCodigo != null ? dscCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveDisciplinaCientifica)) {
            return false;
        }
        InveDisciplinaCientifica other = (InveDisciplinaCientifica) object;
        if ((this.dscCodigo == null && other.dscCodigo != null) || (this.dscCodigo != null && !this.dscCodigo.equals(other.dscCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public InveDisciplinaCientifica clone() throws CloneNotSupportedException {
        return (InveDisciplinaCientifica) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica[ dscCodigo=" + dscCodigo + " ]";
    }

}
