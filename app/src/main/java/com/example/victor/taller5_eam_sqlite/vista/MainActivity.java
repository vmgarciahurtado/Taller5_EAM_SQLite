package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.victor.taller5_eam_sqlite.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView txtBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBienvenido = findViewById(R.id.txtUsuario);

        SharedPreferences preferences = Objects.requireNonNull(this).getSharedPreferences("nombre", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "No existe el valor");
        txtBienvenido.setText("Bienvenido " + nombre);
    }

    public void onclick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.btnRegistrarMarca:
                intent = new Intent(MainActivity.this, RegistrarMarcarActivity.class);
                startActivity(intent);
                break;

            case R.id.btnNuevoIngresoInventario:
                intent = new Intent(MainActivity.this, RegistrarInventarioActivity.class);
                startActivity(intent);
                break;

            case R.id.btnListarInventario:
                intent = new Intent(MainActivity.this, ListaInventarioActivity.class);
                startActivity(intent);
                break;

        }
    }
}
