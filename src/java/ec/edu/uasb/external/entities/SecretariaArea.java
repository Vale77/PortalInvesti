/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author xavier.duque
 */
@Entity
public class SecretariaArea implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PER_CODIGO")
    private Long perCodigo;
    @Basic(optional = false)
 
    @Column(name = "NOMBRE_SECRETARIA")
    private String nombreSecretaria;

    public SecretariaArea() {
    }

    public SecretariaArea(Long perCodigo, String nombreSecretaria) {
        this.perCodigo = perCodigo;
        this.nombreSecretaria = nombreSecretaria;
    }

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getNombreSecretaria() {
        return nombreSecretaria;
    }

    public void setNombreSecretaria(String nombreSecretaria) {
        this.nombreSecretaria = nombreSecretaria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.perCodigo);
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
        final SecretariaArea other = (SecretariaArea) obj;
        return Objects.equals(this.perCodigo, other.perCodigo);
    }

  

    @Override
    public String toString() {
        return "SecretariaArea{" + "perCodigo=" + perCodigo + ", nombreSecretaria=" + nombreSecretaria + '}';
    }
    
    
}
