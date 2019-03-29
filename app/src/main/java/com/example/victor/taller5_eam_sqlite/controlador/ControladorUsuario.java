package com.example.victor.taller5_eam_sqlite.controlador;

import android.app.Activity;

import com.example.victor.taller5_eam_sqlite.DAO.UsuarioDAO;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

public class ControladorUsuario {
    UsuarioDAO dao;

    public ControladorUsuario(Activity activity) {
        this.dao = new UsuarioDAO(activity);
    }

    public boolean guardarUsuario(String nombreCompleto, String nombreUsuario, String password){
        Usuario usuario = new Usuario(nombreCompleto, nombreUsuario, password);
        return dao.guardar(usuario);

    }
    public Usuario buscarUsuario(String nombreUsuario,String contraseña){
        return dao.buscar(nombreUsuario,contraseña);
    }

    public boolean eliminarUsuario(String nombreUsuario){
        Usuario usuario = new Usuario("", nombreUsuario, "");
        return dao.eliminar(usuario);
    }

    public boolean modificarUsuario(String nombreCompleto, String nombreUsuario, String password){
        Usuario usuario = new Usuario(nombreCompleto, nombreUsuario, password);
        return dao.modificar(usuario);
    }

    public boolean buscarUsuarioRepetido(String usuario){
        return dao.buscarRepetido(usuario);
    }
}
