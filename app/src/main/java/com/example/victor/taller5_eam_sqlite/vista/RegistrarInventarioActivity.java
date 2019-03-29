package com.example.victor.taller5_eam_sqlite.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.victor.taller5_eam_sqlite.R;

public class RegistrarInventarioActivity extends AppCompatActivity {

    EditText campoImei,campoNombre;
    Spinner spinnerMarcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);

        campoImei = findViewById(R.id.campoImei);
        campoNombre = findViewById(R.id.campoNombre);
        spinnerMarcar = findViewById(R.id.spinnerMarca);

    }

    public void guardar(View view) {
    }

    public void buscar(View view) {
    }

    public void eliminar(View view) {
    }

    public void modificar(View view) {
    }

    public void listar(View view) {
    }







}
