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

    public void Registrar(View view){
      String nombreCompleto = txtNombreCompleto.getText().toString();
      String nombreUsuario = txtUsuario.getText().toString();
      String password = txtPassword.getText().toString();
      String verPassword = txtVerPassword.getText().toString();
      if(password.equals(verPassword)){

      }else{
          Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
      }
    }
}
