package com.example.victor.taller5_eam_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.victor.taller5_eam_sqlite.infraestructura.Conexion;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class UsuarioDAO {
    Conexion conexion;

    public UsuarioDAO(Activity activity) {
        /*
        Creacion o conexion de la base de datos en caso de que no exista,
        ademas se indica el # de la version anterior y la actual
         */
        conexion = new Conexion(activity);
    }

    public boolean guardar(Usuario usuario) {
        //Objeto que contendra la info a almacenar
        ContentValues registro = new ContentValues();
        registro.put("nombreCompleto", usuario.getNombre());
        registro.put("nombreUsuario", usuario.getUsuario());
        registro.put("password", usuario.getContraseña());
        return conexion.ejecutarInsert("usuario", registro);
    }

    public Usuario buscar(String nombreUsuario,String contraseña) {
        Usuario usuario = null;
        String consulta = "select nombreCompleto from usuario where nombreUsuario=victor and password=victor ";
        //String consulta = "select nombreCompleto from usuario where nombreUsuario= " + nombreUsuario + " and password= " + contraseña;
        Cursor temp = conexion.ejecutarSearch(consulta);

        //Encontro algun registro?
        if (temp.getCount() > 0) {//Si el temp es mayor a cero es porque encontro un dato
            temp.moveToFirst();//Se posiciona en el primer dato que encontro
            usuario = new Usuario(temp.getString(0), nombreUsuario, temp.getString(1));
            //usuario = new Usuario(temp.getString(0), nombreUsuario, temp.getString(0));
            Log.i("===============DATO 0",temp.getString(0));
            Log.i("===============DATO 1",temp.getString(1));
            Log.i("===============DATO 2",temp.getString(2));
            Log.i("===============DATO 3",temp.getString(3));
            /*
            El columnindex es la posicion que tiene ese campo en la tabla.
            */

        }
        conexion.cerrarConexion();
        return usuario;
    }

    public boolean eliminar(Usuario usuario){
        String tabla = "usuario";
        String condicion = "nombreUsuario= "+usuario.getUsuario();
        return conexion.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Usuario usuario){
        String tabla = "usuario";
        String condicion = "nombreUsuario= "+usuario.getUsuario();

        ContentValues registro = new ContentValues();

        registro.put("nombreCompleto", usuario.getNombre());
        registro.put("password", usuario.getContraseña());

        return conexion.ejecutarUpdate(tabla, condicion, registro);
    }

    public boolean buscarRepetido(String usuario){
        String consulta = "select nombreUsuario from usuario where nombreUsuario ="+usuario;
        Cursor temp = conexion.ejecutarSearch(consulta);
        if(temp.getCount()>0){
            return true;
        }else {
            conexion.cerrarConexion();
            return false;
        }
    }

}
