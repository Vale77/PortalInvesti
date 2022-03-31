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
@Table(name = "INVE_LINEA_INVEST_PROYEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveLineaInvestProyec.findAll", query = "SELECT i FROM InveLineaInvestProyec i")
    , @NamedQuery(name = "InveLineaInvestProyec.findByLipCodigo", query = "SELECT i FROM InveLineaInvestProyec i WHERE i.lipCodigo = :lipCodigo")
    , @NamedQuery(name = "InveLineaInvestProyec.findByLipAreaAcad", query = "SELECT i FROM InveLineaInvestProyec i WHERE i.lipAreaAcad = :lipAreaAcad order by i.lipNombre")
    , @NamedQuery(name = "InveLineaInvestProyec.findByLipNombre", query = "SELECT i FROM InveLineaInvestProyec i WHERE i.lipNombre = :lipNombre")})
public class InveLineaInvestProyec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIP_CODIGO")
    private Integer lipCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIP_AREA_ACAD")
    private Short lipAreaAcad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "LIP_NOMBRE")
    private String lipNombre;
    @Column(name = "LIP_COD_ANTERIOR")
    private Integer lipCodAnterior;
    @OneToMany(mappedBy = "inveLineaInvestProyec")
    private Collection<InveCarta> inveCartaCollection;

    public InveLineaInvestProyec() {
    }

    public InveLineaInvestProyec(Integer lipCodigo) {
        this.lipCodigo = lipCodigo;
    }

    public InveLineaInvestProyec(Integer lipCodigo, short lipAreaAcad, String lipNombre) {
        this.lipCodigo = lipCodigo;
        this.lipAreaAcad = lipAreaAcad;
        this.lipNombre = lipNombre;
    }

    public Integer getLipCodigo() {
        return lipCodigo;
    }

    public void setLipCodigo(Integer lipCodigo) {
        this.lipCodigo = lipCodigo;
    }

    public short getLipAreaAcad() {
        return lipAreaAcad;
    }

    public void setLipAreaAcad(short lipAreaAcad) {
        this.lipAreaAcad = lipAreaAcad;
    }

    public String getLipNombre() {
        return lipNombre;
    }

    public void setLipNombre(String lipNombre) {
        this.lipNombre = lipNombre;
    }

    public Integer getLipCodAnterior() {
        return lipCodAnterior;
    }

    public void setLipCodAnterior(Integer lipCodAnterior) {
        this.lipCodAnterior = lipCodAnterior;
    }

    @XmlTransient
    public Collection<InveCarta> getInveCartaCollection() {
        return inveCartaCollection;
    }

    public void setInveCartaCollection(Collection<InveCarta> inveCartaCollection) {
        this.inveCartaCollection = inveCartaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lipCodigo != null ? lipCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveLineaInvestProyec)) {
            return false;
        }
        InveLineaInvestProyec other = (InveLineaInvestProyec) object;
        if ((this.lipCodigo == null && other.lipCodigo != null) || (this.lipCodigo != null && !this.lipCodigo.equals(other.lipCodigo))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveLineaInvestProyec[ lipCodigo=" + lipCodigo + " ]";
    }

}
