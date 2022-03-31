/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_INFORME_OPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveInformeOper.findAll", query = "SELECT i FROM InveInformeOper i")
    , @NamedQuery(name = "InveInformeOper.findByPryCodigo", query = "SELECT i FROM InveInformeOper i WHERE i.pryCodigo = :pryCodigo")})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "infoOperaResults",
            entities = {
                @EntityResult(entityClass = InveInformeOper.class)
                ,
                @EntityResult(entityClass = AreaAcademica.class)
                ,
                @EntityResult(entityClass = InveSeguimiento.class)
            }
    )}
)
public class InveInformeOper implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_CODIGO")
    private Long pryCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IOP_HORAS_ESTIM_SEMAN")
    private Short iopHorasEstimSeman;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IOP_MESES_ESTIM")
    private Double iopMesesEstim;
    @Basic(optional = false)
    @Column(name = "IOP_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iopFechaCrea;
    @Basic(optional = false)
    @Column(name = "IOP_USUARIO_CREA")
    private String iopUsuarioCrea;
    @Column(name = "IOP_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iopFechaAct;
    @Column(name = "IOP_USUARIO_ACT")
    private String iopUsuarioAct;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "TDE_CODIGO", referencedColumnName = "TDE_CODIGO")
    @ManyToOne
    private PrinTipoDedicacion prinTipoDedicacion;
    @Transient
    private AreaAcademica area;
    @Transient
    private InveSeguimiento inveSeguimiento;    

    public InveInformeOper() {
    }

    public InveInformeOper(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public InveInformeOper(Long pryCodigo, Short iopHorasEstimSeman, Double iopMesesEstim) {
        this.pryCodigo = pryCodigo;
        this.iopHorasEstimSeman = iopHorasEstimSeman;
        this.iopMesesEstim = iopMesesEstim;
    }

    public Long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public Short getIopHorasEstimSeman() {
        return iopHorasEstimSeman;
    }

    public void setIopHorasEstimSeman(Short iopHorasEstimSeman) {
        this.iopHorasEstimSeman = iopHorasEstimSeman;
    }

    public Double getIopMesesEstim() {
        return iopMesesEstim;
    }

    public void setIopMesesEstim(Double iopMesesEstim) {
        this.iopMesesEstim = iopMesesEstim;
    }

    public Date getIopFechaCrea() {
        return iopFechaCrea;
    }

    public void setIopFechaCrea(Date iopFechaCrea) {
        this.iopFechaCrea = iopFechaCrea;
    }

    public String getIopUsuarioCrea() {
        return iopUsuarioCrea;
    }

    public void setIopUsuarioCrea(String iopUsuarioCrea) {
        this.iopUsuarioCrea = iopUsuarioCrea;
    }

    public Date getIopFechaAct() {
        return iopFechaAct;
    }

    public void setIopFechaAct(Date iopFechaAct) {
        this.iopFechaAct = iopFechaAct;
    }

    public String getIopUsuarioAct() {
        return iopUsuarioAct;
    }

    public void setIopUsuarioAct(String iopUsuarioAct) {
        this.iopUsuarioAct = iopUsuarioAct;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public PrinTipoDedicacion getPrinTipoDedicacion() {
        return prinTipoDedicacion;
    }

    public void setPrinTipoDedicacion(PrinTipoDedicacion prinTipoDedicacion) {
        this.prinTipoDedicacion = prinTipoDedicacion;
    }

    public AreaAcademica getArea() {
        return area;
    }

    public void setArea(AreaAcademica area) {
        this.area = area;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pryCodigo != null ? pryCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveInformeOper)) {
            return false;
        }
        InveInformeOper other = (InveInformeOper) object;
        return !((this.pryCodigo == null && other.pryCodigo != null) || (this.pryCodigo != null && !this.pryCodigo.equals(other.pryCodigo)));
    }

    @Override
    public InveInformeOper clone() throws CloneNotSupportedException {
        return (InveInformeOper) super.clone();
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveInformeOper[ pryCodigo=" + pryCodigo + " ]";
    }

}
