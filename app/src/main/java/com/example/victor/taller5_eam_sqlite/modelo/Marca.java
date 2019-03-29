package com.example.victor.taller5_eam_sqlite.modelo;

/**
 * Created by victor on 3/28/19.
 */

public class Marca {

    String marca,descripcion;

    public Marca() {
    }

    public Marca(String marca, String descripcion) {
        this.marca = marca;
        this.descripcion = descripcion;
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
