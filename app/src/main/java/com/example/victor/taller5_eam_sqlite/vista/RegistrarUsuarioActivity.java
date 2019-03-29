package com.example.victor.taller5_eam_sqlite.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorUsuario;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    EditText txtNombreCompleto, txtUsuario, txtPassword, txtVerPassword;
    ControladorUsuario controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        txtNombreCompleto = (EditText) findViewById(R.id.txtNombreCompleto);
        txtUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtVerPassword = (EditText) findViewById(R.id.txtVerPassword);
        controlador = new ControladorUsuario(this);
    }

    public void Registrar(View view) {
        String nombreCompleto = txtNombreCompleto.getText().toString();
        String nombreUsuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();
        String verPassword = txtVerPassword.getText().toString();
        if (nombreCompleto.equals("") || nombreUsuario.equals("") || password.equals("") || verPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals(verPassword)) {
                if (controlador.buscarUsuarioRepetido(nombreUsuario) == false) {
                    if (controlador.guardarUsuario(nombreCompleto, nombreUsuario, password)) {
                        limpiarCampos();
                        Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al almacenar la información, intenta de nuevo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "El nombre de usuario ya esta en uso", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        txtVerPassword.setText("");
    }
}
