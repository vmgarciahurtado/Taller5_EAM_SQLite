package com.example.victor.taller5_eam_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.victor.taller5_eam_sqlite.infraestructura.Conexion;
import com.example.victor.taller5_eam_sqlite.modelo.Celular;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

/**
 * Created by victor on 3/28/19.
 */

public class CelularDao {
    Conexion conexion;


    public CelularDao(Activity activity) {
        conexion = new Conexion(activity);
    }

    public boolean guardar(Celular celular) {
        ContentValues registro = new ContentValues();
        registro.put("IMEI", celular.getImei());
        registro.put("marca", celular.getMarca());
        registro.put("nombreCelular", celular.getNombre());
        registro.put("propietario", celular.getPropietario());
        return conexion.ejecutarInsert("usuario", registro);
    }

    public Usuario buscar(String nombreUsuario) {
        Usuario usuario = null;
        String consulta = "select IMEI,marca,nombreCelular from celular where nombreUsuario= " + nombreUsuario;
        Cursor temp = conexion.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            usuario = new Usuario(temp.getString(0), nombreUsuario, temp.getString(2));
        }
        conexion.cerrarConexion();
        return usuario;
    }

}
