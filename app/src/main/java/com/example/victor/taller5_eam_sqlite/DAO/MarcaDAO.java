package com.example.victor.taller5_eam_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.victor.taller5_eam_sqlite.infraestructura.Conexion;
import com.example.victor.taller5_eam_sqlite.modelo.Marca;

public class MarcaDAO {
    Conexion conexion;

    public MarcaDAO(Activity activity) {
        conexion = new Conexion(activity);
    }

    public boolean guardar(Marca marca) {
        //Objeto que contendra la info a almacenar

        ContentValues registro = new ContentValues();
        registro.put("nombreMarca", marca.getMarca());
        registro.put("propietario", marca.getPropietario());
        registro.put("descripcionMarca", marca.getDescripcion());
        return conexion.ejecutarInsert("marca", registro);
    }

    public Marca buscar(String propietario) {
        Marca marca = null;
        String consulta = "select nombreMarca, descripcionMarca from Marca where propietario= '" + propietario+"'";
        Cursor temp = conexion.ejecutarSearch(consulta);

        //Encontro algun registro?
        if (temp.getCount() > 0) {//Si el temp es mayor a cero es porque encontro un dato
            temp.moveToFirst();//Se posiciona en el primer dato que encontro
            marca = new Marca(temp.getString(0), temp.getString(1), propietario);

        }
        conexion.cerrarConexion();
        return marca;
    }

    public boolean eliminar(Marca marca){
        String tabla = "marca";
        String condicion = "propietario= "+marca.getPropietario();
        return conexion.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Marca marca){
        String tabla = "marca";
        String condicion = "propietario= "+marca.getPropietario();

        ContentValues registro = new ContentValues();

        registro.put("nombreMarca", marca.getMarca());
        registro.put("descripcionMarca", marca.getDescripcion());

        return conexion.ejecutarUpdate(tabla, condicion, registro);
    }

    public Marca cargarSpinner(String propietario){
        Marca marca = null;
        String consulta = "select nombreMarca from marca where propietario = '"+propietario+"'";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if(temp.getCount()>0) {
            temp.moveToFirst();
            marca = new Marca(temp.getString(0));
        }else{
            Log.i("ERROR", "No se encontraron datos para cargar el spinner marcaDAO");
        }
        conexion.cerrarConexion();
        return marca;
    }
}
