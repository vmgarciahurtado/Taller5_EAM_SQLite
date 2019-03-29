package com.example.victor.taller5_eam_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistrarMarcarActivity extends AppCompatActivity {
EditText txtNombreMarca, txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_marcar);
        txtNombreMarca = (EditText) findViewById(R.id.txtNombreMarca);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcionMarca);
    }
}
