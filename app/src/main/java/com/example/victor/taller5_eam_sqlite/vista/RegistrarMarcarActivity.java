package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorMarca;

import java.util.Objects;

public class RegistrarMarcarActivity extends AppCompatActivity {
EditText txtNombreMarca, txtDescripcion;
ControladorMarca controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_marcar);
        txtNombreMarca = findViewById(R.id.txtNombreMarca);
        txtDescripcion = findViewById(R.id.txtDescripcionMarca);
        controlador = new ControladorMarca(this);
    }

    public void guardar(View view) {

        SharedPreferences preferences = Objects.requireNonNull(this).getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String propietario = preferences.getString("usuario", "No existe el valor");
        String nombreMarca = txtNombreMarca.getText().toString();
        String descripcion = txtDescripcion.getText().toString();

        if (nombreMarca.equals("") || descripcion.equals("") || propietario.equals("")) {
            Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT).show();
        } else {
                //if (controlador.guardarMarca(nombreUsuario) == false) {
                    if (controlador.guardarMarca(nombreMarca,propietario,descripcion)) {
                        limpiarCampos();
                        Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al almacenar la información, intenta de nuevo", Toast.LENGTH_SHORT).show();
                    }

                //} else {
                //    Toast.makeText(getApplicationContext(), "El nombre de usuario ya esta en uso", Toast.LENGTH_SHORT).show();
                //}
        }
    }

    private void limpiarCampos() {
        txtDescripcion.setText(null);
        txtNombreMarca.setText(null);
    }
}
