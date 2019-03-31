package com.example.victor.taller5_eam_sqlite.controlador;

import android.app.Activity;

import com.example.victor.taller5_eam_sqlite.DAO.MarcaDAO;
import com.example.victor.taller5_eam_sqlite.modelo.Marca;

public class ControladorMarca {
    MarcaDAO dao;

    public ControladorMarca(Activity activity) {
        this.dao = new MarcaDAO(activity);
    }

    public boolean guardarMarca(String nombreMarca, String propietario, String descripcionMarca){
        Marca marca  = new Marca(nombreMarca, descripcionMarca, propietario);
        return dao.guardar(marca);

    }
    public Marca buscarMarca(String propietario){
        return dao.buscar(propietario);
    }

    public boolean eliminarMarca(String propietario){
        Marca marca = new Marca("", "", propietario);
        return dao.eliminar(marca);
    }
    public boolean modificarMarca(String nombreMarca, String propietario, String descripcionMarca){
        Marca marca  = new Marca(nombreMarca, descripcionMarca, propietario);
        return dao.modificar(marca);
    }
    public Marca listarSpinner(String propietario){
        return dao.cargarSpinner(propietario);
    }
}
