package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorCelular;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorMarca;
import com.example.victor.taller5_eam_sqlite.modelo.Celular;
import com.example.victor.taller5_eam_sqlite.modelo.Marca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistrarInventarioActivity extends AppCompatActivity {

    EditText campoImei, campoNombre;
    TextView campoMarca;
    RelativeLayout layout;
    ImageView editarMarca;
    Spinner spinnerMarcar;
    ControladorCelular controlador;
    ControladorMarca controladorMarca;
    List<Marca> marcas;
    ArrayList<String> listaMarca;
    String propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);
        SharedPreferences preferences = Objects.requireNonNull(this).getSharedPreferences("usuario", Context.MODE_PRIVATE);
        propietario = preferences.getString("usuario", "No existe el valor");
        campoImei = findViewById(R.id.campoImei);
        campoNombre = findViewById(R.id.campoNombre);
        campoMarca = findViewById(R.id.campoMarca);
        editarMarca = findViewById(R.id.imagenEditar);
        editarMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerMarcar.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);
            }
        });
        spinnerMarcar = findViewById(R.id.spinnerMarca);
        controlador = new ControladorCelular(this);
        layout = findViewById(R.id.layoutSpinner);
        controladorMarca = new ControladorMarca(this);
        consultarMarcas();
    }

    public void guardar(View view) {
        String IMEI = campoImei.getText().toString();
        String marca = spinnerMarcar.getSelectedItem().toString();
        String nombre = campoNombre.getText().toString();
        if (controlador.guardarCelular(IMEI, marca, nombre, propietario)) {
            vaciarCampos();
            Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error al almacenar la información, intenta de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar(View view) {
        String IMEI = campoImei.getText().toString();
        if (!IMEI.equals("")) {
            Celular celular = controlador.buscarPorImei(IMEI);
            if (celular != null) {
                campoImei.setEnabled(false);
                layout.setVisibility(View.VISIBLE);
                spinnerMarcar.setVisibility(View.INVISIBLE);
                //  for (int i = 0; i<spinnerMarcar.getCount();i++){
                //   spinnerMarcar.getItemAtPosition(i).toString().equalsIgnoreCase(celular.getMarca());
                // }
                campoNombre.setText(celular.getNombre());
                campoMarca.setText(celular.getMarca());
                layout.setVisibility(View.VISIBLE);
                spinnerMarcar.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "No existe ningun celular con el IMEI " + IMEI, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "El campo IMEI esta vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view) {
        String IMEI = campoImei.getText().toString();
        if (!IMEI.equals("")) {
            if (controlador.eliminarPorImei(IMEI)) {
                vaciarCampos();
                Toast.makeText(getApplicationContext(), "Los datos se eliminaron correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "El campo IMEI esta vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar(View view) {
        String IMEI = campoImei.getText().toString();
        String marca = spinnerMarcar.getSelectedItem().toString();
        String nombre = campoNombre.getText().toString();
        spinnerMarcar.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);

        if(IMEI!=null || marca !=null || nombre!=null){
            if(controlador.modificarCelular(IMEI, marca, nombre, propietario)){
                vaciarCampos();
                Toast.makeText(getApplicationContext(), "Los datos se modificaron correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error al modificar el celular, intenta más tarde", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(View view) {
        Intent intent = new Intent(this, ListaInventarioActivity.class);
        startActivity(intent);
        vaciarCampos();
    }


    private void consultarMarcas() {
        marcas = controladorMarca.listarMarca(propietario);
        List<String> comboAdapter = new ArrayList<>();
        ArrayAdapter<String> adapter;
        if (marcas.size() > 0) {
            for (int i = 0; i < marcas.size(); i++) {
                comboAdapter.add(marcas.get(i).getMarca());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comboAdapter);
            spinnerMarcar.setAdapter(adapter);
        }
    }

    public void vaciarCampos() {
        campoImei.setEnabled(true);
        campoImei.setText("");
        campoNombre.setText("");
    }


}
