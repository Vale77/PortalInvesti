/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_CRONOGRAMA_MENSUAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveCronogramaMensual.findAll", query = "SELECT i FROM InveCronogramaMensual i")
    , @NamedQuery(name = "InveCronogramaMensual.findByCroCodigo", query = "SELECT i FROM InveCronogramaMensual i WHERE i.croCodigo = :croCodigo")
    , @NamedQuery(name = "InveCronogramaMensual.findByCroActividad", query = "SELECT i FROM InveCronogramaMensual i WHERE i.croActividad = :croActividad")})
public class InveCronogramaMensual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRO_CODIGO")
    private Long croCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8000)
    @Column(name = "CRO_ACTIVIDAD")
    private String croActividad;
    @Size(max = 1)
    @Column(name = "CRO_MES_1")
    private String croMes1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CRO_MONTO_1")
    private BigDecimal croMonto1;
    @Size(max = 1)
    @Column(name = "CRO_MES_2")
    private String croMes2;
    @Column(name = "CRO_MONTO_2")
    private BigDecimal croMonto2;
    @Size(max = 1)
    @Column(name = "CRO_MES_3")
    private String croMes3;
    @Column(name = "CRO_MONTO_3")
    private BigDecimal croMonto3;
    @Size(max = 1)
    @Column(name = "CRO_MES_4")
    private String croMes4;
    @Column(name = "CRO_MONTO_4")
    private BigDecimal croMonto4;
    @Size(max = 1)
    @Column(name = "CRO_MES_5")
    private String croMes5;
    @Column(name = "CRO_MONTO_5")
    private BigDecimal croMonto5;
    @Size(max = 1)
    @Column(name = "CRO_MES_6")
    private String croMes6;
    @Column(name = "CRO_MONTO_6")
    private BigDecimal croMonto6;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO")
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @Transient
    private Boolean[] meses;

    public InveCronogramaMensual() {
    }

    public InveCronogramaMensual(Boolean[] meses) {
        this.meses = meses;
    }

    public InveCronogramaMensual(Long croCodigo) {
        this.croCodigo = croCodigo;
    }

    public InveCronogramaMensual(Long croCodigo, String croActividad) {
        this.croCodigo = croCodigo;
        this.croActividad = croActividad;
    }

    public Long getCroCodigo() {
        return croCodigo;
    }

    public void setCroCodigo(Long croCodigo) {
        this.croCodigo = croCodigo;
    }

    public String getCroActividad() {
        return croActividad;
    }

    public void setCroActividad(String croActividad) {
        this.croActividad = croActividad;
    }

    public String getCroMes1() {
        return croMes1;
    }

    public void setCroMes1(String croMes1) {
        this.croMes1 = croMes1;
    }

    public BigDecimal getCroMonto1() {
        return croMonto1;
    }

    public void setCroMonto1(BigDecimal croMonto1) {
        this.croMonto1 = croMonto1;
    }

    public String getCroMes2() {
        return croMes2;
    }

    public void setCroMes2(String croMes2) {
        this.croMes2 = croMes2;
    }

    public BigDecimal getCroMonto2() {
        return croMonto2;
    }

    public void setCroMonto2(BigDecimal croMonto2) {
        this.croMonto2 = croMonto2;
    }

    public String getCroMes3() {
        return croMes3;
    }

    public void setCroMes3(String croMes3) {
        this.croMes3 = croMes3;
    }

    public BigDecimal getCroMonto3() {
        return croMonto3;
    }

    public void setCroMonto3(BigDecimal croMonto3) {
        this.croMonto3 = croMonto3;
    }

    public String getCroMes4() {
        return croMes4;
    }

    public void setCroMes4(String croMes4) {
        this.croMes4 = croMes4;
    }

    public BigDecimal getCroMonto4() {
        return croMonto4;
    }

    public void setCroMonto4(BigDecimal croMonto4) {
        this.croMonto4 = croMonto4;
    }

    public String getCroMes5() {
        return croMes5;
    }

    public void setCroMes5(String croMes5) {
        this.croMes5 = croMes5;
    }

    public BigDecimal getCroMonto5() {
        return croMonto5;
    }

    public void setCroMonto5(BigDecimal croMonto5) {
        this.croMonto5 = croMonto5;
    }

    public String getCroMes6() {
        return croMes6;
    }

    public void setCroMes6(String croMes6) {
        this.croMes6 = croMes6;
    }

    public BigDecimal getCroMonto6() {
        return croMonto6;
    }

    public void setCroMonto6(BigDecimal croMonto6) {
        this.croMonto6 = croMonto6;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public Boolean[] getMeses() {
        return meses;
    }

    public void setMeses(Boolean[] meses) {
        this.meses = meses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (croCodigo != null ? croCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveCronogramaMensual)) {
            return false;
        }
        InveCronogramaMensual other = (InveCronogramaMensual) object;
        if ((this.croCodigo == null && other.croCodigo != null) || (this.croCodigo != null && !this.croCodigo.equals(other.croCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InveCronogramaMensual{" + "croCodigo=" + croCodigo
                + ", croActividad=" + croActividad 
                + ", croMes1=" + croMes1
                + ", croMes2=" + croMes2 
                + ", croMes3=" + croMes3 
                + ", croMes4=" + croMes4 
                + ", croMes5=" + croMes5 
                + ", croMes6=" + croMes6 
                + '}';
    }


    
}
