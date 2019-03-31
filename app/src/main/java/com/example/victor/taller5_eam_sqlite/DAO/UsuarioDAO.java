package com.example.victor.taller5_eam_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.example.victor.taller5_eam_sqlite.infraestructura.Conexion;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

public class UsuarioDAO {
    Conexion conexion;

    public UsuarioDAO(Activity activity){
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

    public Usuario buscar(String nombreUsuario, String contraseña) {

        Log.i("******USUARIO",nombreUsuario.toString());
        Log.i("******CONTRASENA",contraseña.toString());

        Usuario usuario = null;
        //String consulta = "select nombreCompleto,nombreUsuario,password " + "from usuario where " +" " +  " usuario.nombreUsuario = "+"nombreUsuario";
        String consulta = "select nombreCompleto from usuario where nombreUsuario = 'victorsmanuels'";
        //String consulta = "select nombreCompleto " + " " + " from " + " " + " usuario " + " " + " where " + " " + " nombreUsuario = " + nombreUsuario;
       // String consulta = "select nombreCompleto from usuario where nombreUsuario= " + nombreUsuario + " and password= " + contraseña;
        Cursor temp = conexion.ejecutarSearch(consulta);

      //  try {
         if (temp.getCount() > 0){//Si el temp es mayor a cero es porque encontro un dato
                temp.moveToFirst();//Se posiciona en el primer dato que encontro
                usuario = new Usuario(temp.getString(0),"","");

                  Log.i("===============DATO 0", temp.getString(0));
//                Log.i("===============DATO 1", temp.getString(1));
//                Log.i("===============DATO 2", temp.getString(2));
//                Log.i("===============DATO 3", temp.getString(3));


                //usuario = new Usuario(temp.getString(0), nombreUsuario, temp.getString(0));

            /*
            El columnindex es la posicion que tiene ese campo en la tabla.
            */
            }

            conexion.cerrarConexion();
      //  } catch (Exception e) {
      //      Log.i("************ ERROr", e.toString());
      //  }


        //Encontro algun registro?
        return usuario;
    }


    public boolean eliminar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "nombreUsuario= " + usuario.getUsuario();
        return conexion.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "nombreUsuario= " + usuario.getUsuario();

        ContentValues registro = new ContentValues();

        registro.put("nombreCompleto", usuario.getNombre());
        registro.put("password", usuario.getContraseña());

        return conexion.ejecutarUpdate(tabla, condicion, registro);
    }


    public boolean buscarRepetido(String usuario) {

        boolean respuesta =  false;

        try {
            String consulta = "select nombreUsuario from usuario where nombreUsuario =" + usuario;
            Cursor temp = conexion.ejecutarSearch(consulta);
            if (temp.getCount() > 0) {
                respuesta = true;
                //return true;
            } else {
                conexion.cerrarConexion();
                respuesta = false;
                //return false;
            }
        } catch (Exception e) {
            Log.i("********ERROR REPETIDO", e.toString());
        }

        return respuesta;
    }

}
