package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorCelular;
import com.example.victor.taller5_eam_sqlite.modelo.Celular;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaInventarioActivity extends AppCompatActivity {

    private ListView lstUsuarios;
    ControladorCelular controlador;
    List<Celular> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inventario);

        controlador = new ControladorCelular(this);
        lstUsuarios = findViewById(R.id.listView);
        configurarLista();
    }

    private void configurarLista() {
        SharedPreferences preferences = Objects.requireNonNull(this).getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String propietario = preferences.getString("usuario", "No existe el valor");

        listaUsuarios = controlador.listarCelulares(propietario);
        List<String> listaAdapter = new ArrayList<>();
        ArrayAdapter<String> adapter;
        if (listaUsuarios.size() > 0) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                listaAdapter.add(listaUsuarios.get(i).getImei() + "-"
                        + listaUsuarios.get(i).getMarca() + "-"
                        + listaUsuarios.get(i).getNombre());
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
            lstUsuarios.setAdapter(adapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
            lstUsuarios.setAdapter(adapter);
        }
        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarInformacion(position);
            }
        });

    }

    private void mostrarInformacion(int position) {
        Toast.makeText(this, "IMEI: " + listaUsuarios.get(position).getImei() + "\n" +
                "MARCA: " + listaUsuarios.get(position).getMarca() + "\n" +
                "NOMBRE: " + listaUsuarios.get(position).getNombre() + "\n" +
                "PROPIETARIO: " + listaUsuarios.get(position).getPropietario() + "\n", Toast.LENGTH_SHORT).show();
    }
}
