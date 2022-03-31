/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author xavier.duque
 */
@Entity
@Table(name = "AREA", schema = "interfaseOcu.")
public class AreaAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ARE_CODIGO")
    private Short areaCodigo;
    @Column(name = "NOMBRE_AREA")
    private String areaNombre;

    public Short getAreaCodigo() {
        return areaCodigo;
    }

    public void setAreaCodigo(Short areaCodigo) {
        this.areaCodigo = areaCodigo;
    }

    public String getAreaNombre() {
        return areaNombre;
    }

    public void setAreaNombre(String areaNombre) {
        this.areaNombre = areaNombre;
    }

    public AreaAcademica() {

    }

    public AreaAcademica(Short areaCodigo, String areaNombre) {
        this.areaCodigo = areaCodigo;
        this.areaNombre = areaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.areaCodigo);
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
        final AreaAcademica other = (AreaAcademica) obj;
        if (!Objects.equals(this.areaCodigo, other.areaCodigo)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "AreaAcademica{" + "areaCodigo=" + areaCodigo + ", areaNombre=" + areaNombre + '}';
    }
    
    

}
