package com.example.victor.taller5_eam_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.victor.taller5_eam_sqlite.infraestructura.Conexion;
import com.example.victor.taller5_eam_sqlite.modelo.Celular;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

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
        registro.put("IdMarca", celular.getIdMarca());
        return conexion.ejecutarInsert("celular", registro);
    }

    public Celular buscar(String propietario) {
        Celular celular = null;
        String consulta = "select IMEI, marca, propietario, nombreCelular from celular where propietario= " + propietario;
        Cursor temp = conexion.ejecutarSearch(consulta);

        //Encontro algun registro?
        if (temp.getCount() > 0) {//Si el temp es mayor a cero es porque encontro un dato
            temp.moveToFirst();//Se posiciona en el primer dato que encontro
            celular = new Celular(temp.getString(0), temp.getString(1), temp.getString(2), propietario,temp.getString(3));
            /*
            El columnindex es la posicion que tiene ese campo en la tabla.
            */

        }
        conexion.cerrarConexion();
        return celular;
    }

    public Celular buscarPorImei(String imei) {
        Celular celular = null;
        String consulta = "select IMEI, marca, nombreCelular from celular where IMEI= '" + imei + "'";
        Cursor temp = conexion.ejecutarSearch(consulta);
        if (temp.getCount() >= 0) {
            temp.moveToFirst();
            celular = new Celular(imei, temp.getString(1), temp.getString(2));
        }
        conexion.cerrarConexion();
        return celular;
    }

    public boolean eliminar(Celular celular) {
        String tabla = "marca";
        String condicion = "propietario= " + celular.getPropietario();
        return conexion.ejecutarDelete(tabla, condicion);
    }
    public boolean eliminarPorImei(Celular celular){
        String tabla = "celular";
        String condicion = "IMEI= "+celular.getImei();
        return conexion.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Celular celular) {
        String tabla = "celular";
        String condicion = "IMEI= " + celular.getImei();

        ContentValues registro = new ContentValues();

        registro.put("marca", celular.getMarca());
        registro.put("propietario", celular.getPropietario());
        registro.put("nombreCelular", celular.getNombre());
        registro.put("nombreCelular", celular.getNombre());
        registro.put("IdMarca", celular.getIdMarca());

        return conexion.ejecutarUpdate(tabla, condicion, registro);
    }

    public List<Celular> listar(String nombreUsuario) {
        List<Celular> listaCelulares = new ArrayList<>();
        String consulta = "select IMEI,marca,nombreCelular,propietario from celular where propietario = " + "'" + nombreUsuario + "'";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.moveToFirst()) {
            do {
                Celular celular = new Celular(temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3),"");//,(temp.getString(3)),temp.getString(4)
                listaCelulares.add(celular);
            } while (temp.moveToNext());
        }
        return listaCelulares;
    }

    public boolean buscarRepetido(String IMEI) {
        boolean respuesta =  false;
        try {
            String consulta = "select imei from celular where imei = "+"'"+IMEI+"'";
            Cursor temp = conexion.ejecutarSearch(consulta);
            if (temp.getCount() > 0) {
                respuesta = true;
            } else {
                conexion.cerrarConexion();
                respuesta = false;
            }
        } catch (Exception e) {
            Log.i("********ERROR REPETIDO", e.toString());
        }
        return respuesta;
    }

}
