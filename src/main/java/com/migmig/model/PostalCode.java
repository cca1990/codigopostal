package com.migmig.model;

/**
 * Created by mramos on 1/22/2016.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "codigospostales")
public class PostalCode {

    @Id
    @Column(name="CODIGOPOSTAL")
    private long codigoPostal;

    @Column(name="COLONIA")
    private String colonia;

    @Column(name="MUNICIPIO")
    private String municipio;

    @Column(name="ESTADO")
    private String estado;

    public long getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(long codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
