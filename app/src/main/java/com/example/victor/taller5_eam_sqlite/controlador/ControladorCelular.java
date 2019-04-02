package com.example.victor.taller5_eam_sqlite.controlador;

import android.app.Activity;

import com.example.victor.taller5_eam_sqlite.DAO.CelularDao;
import com.example.victor.taller5_eam_sqlite.modelo.Celular;

import java.util.List;

/**
 * Created by victor on 3/28/19.
 */

public class ControladorCelular {

    CelularDao dao;

    public ControladorCelular(Activity activity){
        dao = new CelularDao(activity);
    }

    public boolean guardarCelular(String imei,String marca,String nombre,String propietario){
        Celular celular = new Celular(imei,marca,nombre,propietario);
        return dao.guardar(celular);
    }
    public Celular buscarCelular(String propietario){
        return dao.buscar(propietario);
    }

    public Celular buscarPorImei(String IMEI){
        return dao.buscarPorImei(IMEI);
    }

    public boolean eliminarCelular(String propietario){
        Celular celular = new Celular("", "", "", propietario);
        return dao.eliminar(celular);
    }

    public boolean eliminarPorImei(String IMEI){
        Celular celular = new Celular(IMEI,"","","");
        return  dao.eliminarPorImei(celular);
    }

    public boolean modificarCelular(String imei,String marca,String nombre,String propietario){
        Celular celular = new Celular(imei,marca,nombre,propietario);
        return dao.modificar(celular);
    }

    public List<Celular> listarCelulares(String propietario){
        return dao.listar(propietario);
    }

    public boolean buscarCelularRepetido(String IMEI){
        return dao.buscarRepetido(IMEI);
    }
}
