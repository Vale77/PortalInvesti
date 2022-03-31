/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_CONVOCAT_GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveConvocatGrupo.findAll", query = "SELECT i FROM InveConvocatGrupo i")
    , @NamedQuery(name = "InveConvocatGrupo.findByCvoCodigo", query = "SELECT i FROM InveConvocatGrupo i WHERE i.inveConvocatGrupoPK.cvoCodigo = :cvoCodigo")
    , @NamedQuery(name = "InveConvocatGrupo.findByGrpCodigo", query = "SELECT i FROM InveConvocatGrupo i WHERE i.inveConvocatGrupoPK.grpCodigo = :grpCodigo")})
public class InveConvocatGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InveConvocatGrupoPK inveConvocatGrupoPK;
    @Column(name = "CGR_ANIO_CONSULTA")
    private Integer cgrAnioConsulta;
    @Column(name = "CGR_ANIOS_ANTIGUEDAD")
    private Integer cgrAniosAntiguedad;

    @JoinColumn(name = "CVO_CODIGO", referencedColumnName = "CVO_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveConvocatoria inveConvocatoria;

    @JoinColumn(name = "GRP_CODIGO", referencedColumnName = "GRP_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveGrupo inveGrupo;

    public InveConvocatGrupo() {
    }

    public InveConvocatGrupo(InveConvocatGrupoPK inveConvocatGrupoPK) {
        this.inveConvocatGrupoPK = inveConvocatGrupoPK;
    }

    public InveConvocatGrupo(long cvoCodigo, short grpCodigo) {
        this.inveConvocatGrupoPK = new InveConvocatGrupoPK(cvoCodigo, grpCodigo);
    }

    public InveConvocatGrupoPK getInveConvocatGrupoPK() {
        return inveConvocatGrupoPK;
    }

    public void setInveConvocatGrupoPK(InveConvocatGrupoPK inveConvocatGrupoPK) {
        this.inveConvocatGrupoPK = inveConvocatGrupoPK;
    }

    public InveConvocatoria getInveConvocatoria() {
        return inveConvocatoria;
    }

    public void setInveConvocatoria(InveConvocatoria inveConvocatoria) {
        this.inveConvocatoria = inveConvocatoria;
    }

    public InveGrupo getInveGrupo() {
        return inveGrupo;
    }

    public void setInveGrupo(InveGrupo inveGrupo) {
        this.inveGrupo = inveGrupo;
    }

    public Integer getCgrAnioConsulta() {
        return cgrAnioConsulta;
    }

    public void setCgrAnioConsulta(Integer cgrAnioConsulta) {
        this.cgrAnioConsulta = cgrAnioConsulta;
    }

    public Integer getCgrAniosAntiguedad() {
        return cgrAniosAntiguedad;
    }

    public void setCgrAniosAntiguedad(Integer cgrAniosAntiguedad) {
        this.cgrAniosAntiguedad = cgrAniosAntiguedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inveConvocatGrupoPK != null ? inveConvocatGrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveConvocatGrupo)) {
            return false;
        }
        InveConvocatGrupo other = (InveConvocatGrupo) object;
        return !((this.inveConvocatGrupoPK == null && other.inveConvocatGrupoPK != null) || (this.inveConvocatGrupoPK != null && !this.inveConvocatGrupoPK.equals(other.inveConvocatGrupoPK)));
    }

    @Override
    public String toString() {
        return "InveConvocatGrupo{" + "inveConvocatGrupoPK=" + inveConvocatGrupoPK + ", inveConvocatoria=" + inveConvocatoria + ", inveGrupo=" + inveGrupo + '}';
    }

}
