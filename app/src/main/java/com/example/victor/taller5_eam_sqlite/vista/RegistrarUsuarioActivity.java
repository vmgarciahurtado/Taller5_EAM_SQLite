package com.example.victor.taller5_eam_sqlite.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.victor.taller5_eam_sqlite.R;

public class RegistrarUsuarioActivity extends AppCompatActivity {
EditText txtNombreCompleto, txtUsuario, txtPassword, txtVerPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        txtNombreCompleto = (EditText) findViewById(R.id.txtNombreCompleto);
        txtUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtVerPassword = (EditText) findViewById(R.id.txtVerPassword);
    }
}
