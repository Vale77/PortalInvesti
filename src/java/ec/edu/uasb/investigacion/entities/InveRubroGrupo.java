/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_RUBRO_GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveRubroGrupo.findAll", query = "SELECT i FROM InveRubroGrupo i ")
    , @NamedQuery(name = "InveRubroGrupo.findByGrpCodigo", query = "SELECT i FROM InveRubroGrupo i WHERE i.inveRubroGrupoPK.grpCodigo = :grpCodigo ")
    , @NamedQuery(name = "InveRubroGrupo.findByRegistrable", query = "SELECT i FROM InveRubroGrupo i WHERE i.inveRubroGrupoPK.grpCodigo = :grpCodigo and i.rpg_registra = 'S'")})
public class InveRubroGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InveRubroGrupoPK inveRubroGrupoPK;
    @Column(name = "RPG_REGISTRA")
    private String rpg_registra;
    @JoinColumn(name = "GRP_CODIGO", referencedColumnName = "GRP_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveGrupo inveGrupo;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "RUB_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveRubro inveRubro;

    public InveRubroGrupo() {
    }

    public InveRubroGrupo(InveRubroGrupoPK inveRubroGrupoPK) {
        this.inveRubroGrupoPK = inveRubroGrupoPK;
    }

    public InveRubroGrupo(Short grpCodigo, Short rubCodigo) {
        this.inveRubroGrupoPK = new InveRubroGrupoPK(grpCodigo, rubCodigo);
    }

    public InveRubroGrupoPK getInveRubroGrupoPK() {
        return inveRubroGrupoPK;
    }

    public void setInveRubroGrupoPK(InveRubroGrupoPK inveRubroGrupoPK) {
        this.inveRubroGrupoPK = inveRubroGrupoPK;
    }

    public InveGrupo getInveGrupo() {
        return inveGrupo;
    }

    public void setInveGrupo(InveGrupo inveGrupo) {
        this.inveGrupo = inveGrupo;
    }

    public InveRubro getInveRubro() {
        return inveRubro;
    }

    public void setInveRubro(InveRubro inveRubro) {
        this.inveRubro = inveRubro;
    }

    public String getRpg_registra() {
        return rpg_registra;
    }

    public void setRpg_registra(String rpg_registra) {
        this.rpg_registra = rpg_registra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.inveRubroGrupoPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InveRubroGrupo other = (InveRubroGrupo) obj;
        if (!Objects.equals(this.inveRubroGrupoPK, other.inveRubroGrupoPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.InveRubroGrupo[ inveRubroGrupoPK=" + inveRubroGrupoPK + " ]";
    }

}
