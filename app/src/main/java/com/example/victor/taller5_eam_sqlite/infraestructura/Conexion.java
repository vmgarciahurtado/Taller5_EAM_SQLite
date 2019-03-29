package com.example.victor.taller5_eam_sqlite.infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victor on 3/28/19.
 */

public class Conexion extends SQLiteOpenHelper {
    private static final String database = "gestionCelulares.db";
    //Para manipular el registro que retorna la base de datos
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    //Instancia de la base de datos
    SQLiteDatabase bd;

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Conexion(Context context) {
        super(context, database, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "nombreCompleto text, " +
                "nombreUsuario text primary key, " +
                "password text" +
                ")"
        );
        db.execSQL("create table marca(" +
                "nombreMarca text, " +
                "descripcionMarcar text" +
                ")"
        );
        db.execSQL("create table celular(" +
                "IMEI text primary key," +
                "marca text, " +
                "nombreCelular text" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists marca");
        db.execSQL("drop table if exists celular");
        onCreate(db);
    }

    public void cerrarConexion() {
        bd.close();
    }

    public boolean ejecutarInsert(String tabla, ContentValues registro){
        try {
            //Objeto para lectura y escritura en la base de datos
            bd = this.getWritableDatabase();

            //null es los campos que no se van a registrar, y retorna -1 si hubo un error
            int res = (int) bd.insert(tabla, null, registro);
            cerrarConexion();
            if(res!=-1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    public boolean ejecutarDelete(String tabla, String condicion){
        bd = this.getWritableDatabase();
        /*Si la clausula del where - condicion esta con ?, en este otro parametro
        se envian los datos,
        ejemplo
        db.delete("tablename","id=? and name =?", new String[]{"1", "Jack"}
         */
        int cant = bd.delete(tabla, condicion, null);
        cerrarConexion();
        if(cant >=1){
            return true;
        }else{
            return false;
        }

    }
    public boolean ejecutarUpdate(String tabla, String condicion, ContentValues registro){
        try{
            bd=this.getWritableDatabase();
            int cant = bd.update(tabla, registro, condicion, null);
            cerrarConexion();
            if(cant ==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    public Cursor ejecutarSearch(String consulta){
        try{
            //Objeto para lectura y escritura en la base de datos
            bd=this.getWritableDatabase();
            /*
            Definimos un objeto de tipo cursor que almacena la info de la bd, ademas ejecutamos una
            consulta sql
            En el null se especifican los parametros, dado el caso que el SQL no, como con
             */
            Cursor fila = bd.rawQuery(consulta, null);
            return fila;
        }catch (Exception e){
            cerrarConexion();
            return null;
        }
    }
}
