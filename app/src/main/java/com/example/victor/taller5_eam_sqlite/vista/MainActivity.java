package com.example.victor.taller5_eam_sqlite.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.victor.taller5_eam_sqlite.R;

public class MainActivity extends AppCompatActivity {
EditText txtNombreUsuario, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }
    
}
