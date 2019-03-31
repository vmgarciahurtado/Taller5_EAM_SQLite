package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorCelular;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorMarca;
import com.example.victor.taller5_eam_sqlite.modelo.Marca;

import java.util.ArrayList;
import java.util.Objects;

public class RegistrarInventarioActivity extends AppCompatActivity {

    EditText campoImei, campoNombre;
    Spinner spinnerMarcar;
    ControladorCelular controlador;
    ControladorMarca controladorMarca;
    ArrayList<Marca> marcas;
    ArrayList<String>listaMarca;
    String propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);
        SharedPreferences preferences = Objects.requireNonNull(this).getSharedPreferences("usuario", Context.MODE_PRIVATE);
        propietario = preferences.getString("usuario", "No existe el valor");
        campoImei = findViewById(R.id.campoImei);
        campoNombre = findViewById(R.id.campoNombre);
        spinnerMarcar = findViewById(R.id.spinnerMarca);
        controlador = new ControladorCelular(this);
        controladorMarca = new ControladorMarca(this);
        consultarMarcas();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaMarca);
        spinnerMarcar.setAdapter(adapter);
    }

    public void guardar(View view, String propietario) {
        String IMEI = campoImei.getText().toString();
        String marca = spinnerMarcar.getSelectedItem().toString();
        String nombre = campoNombre.getText().toString();
        if (controlador.guardarCelular(IMEI, marca, nombre, propietario)) {
            Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error al almacenar la información, intenta de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar(View view) {
    }

    public void eliminar(View view) {
    }

    public void modificar(View view) {
    }

    public void listar(View view) {
    }

    private void consultarMarcas() {
        marcas = new ArrayList<Marca>();
        Marca marca = controladorMarca.listarSpinner(propietario);
        if(marca!=null){
            marcas.add(marca);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaMarca = new ArrayList<String>();
        listaMarca.add("Selecciona una marca");
        for (int i=0; i<marcas.size();i++){
            listaMarca.add(marcas.get(i).getMarca());
        }
    }

}
