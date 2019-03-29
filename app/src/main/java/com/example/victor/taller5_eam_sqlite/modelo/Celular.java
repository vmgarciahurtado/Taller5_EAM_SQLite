package com.example.victor.taller5_eam_sqlite.modelo;

/**
 * Created by victor on 3/28/19.
 */

public class Celular {

    public Celular(String imei, String marca, String nombre, String usuario, String propietario) {
        this.imei = imei;
        this.marca = marca;
        this.nombre = nombre;
        this.usuario = usuario;
        this.propietario = propietario;
    }

    public Celular() {

    }

    String imei,marca,nombre,usuario,propietario;

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
