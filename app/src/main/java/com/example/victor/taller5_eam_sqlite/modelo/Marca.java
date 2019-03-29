package com.example.victor.taller5_eam_sqlite.modelo;

/**
 * Created by victor on 3/28/19.
 */

public class Marca {

    String marca,descripcion,propietario;

    public Marca() {
    }

    public Marca(String marca, String descripcion ,String propietario) {
        this.marca = marca;
        this.descripcion = descripcion;
        this.propietario = propietario;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
