/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.external.entities.Programa;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_PROGRAMA_PROYECTO")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "progProyResults",
            entities = {
                @EntityResult(entityClass = InveProgramaProyecto.class)
                ,
                @EntityResult(entityClass = Programa.class)
            }
    )}
)
public class InveProgramaProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InveProgramaProyectoPK inveProgramaProyectoPK;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveCarta inveCarta;
    @Transient
    private Programa programa;

    public InveProgramaProyecto() {
    }

    public InveProgramaProyecto(InveProgramaProyectoPK inveProgramaProyectoPK) {
        this.inveProgramaProyectoPK = inveProgramaProyectoPK;
    }

    public InveProgramaProyecto(long pryCodigo, long prgCodigo) {
        this.inveProgramaProyectoPK = new InveProgramaProyectoPK(pryCodigo, prgCodigo);
    }

    public InveProgramaProyectoPK getInveProgramaProyectoPK() {
        return inveProgramaProyectoPK;
    }

    public void setInveProgramaProyectoPK(InveProgramaProyectoPK inveProgramaProyectoPK) {
        this.inveProgramaProyectoPK = inveProgramaProyectoPK;
    }

    public InveCarta getInveCarta() {
        return inveCarta;
    }

    public void setInveCarta(InveCarta inveCarta) {
        this.inveCarta = inveCarta;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inveProgramaProyectoPK != null ? inveProgramaProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveProgramaProyecto)) {
            return false;
        }
        InveProgramaProyecto other = (InveProgramaProyecto) object;
        if ((this.inveProgramaProyectoPK == null && other.inveProgramaProyectoPK != null) || (this.inveProgramaProyectoPK != null && !this.inveProgramaProyectoPK.equals(other.inveProgramaProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication10.InveProgramaProyecto[ inveProgramaProyectoPK=" + inveProgramaProyectoPK + " ]";
    }

}
