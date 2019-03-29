package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.taller5_eam_sqlite.R;
import com.example.victor.taller5_eam_sqlite.controlador.ControladorUsuario;
import com.example.victor.taller5_eam_sqlite.modelo.Usuario;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText campoUsuario,campoContraseña;
    ControladorUsuario controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoUsuario = findViewById(R.id.campousuario);
        campoContraseña = findViewById(R.id.campoContraseña);
        controlador = new ControladorUsuario(this);
    }

    public void buscar(View view) {
        
        if (campoUsuario != null && campoContraseña != null ){
            String usu = campoUsuario.getText().toString();
            String contraseña = campoContraseña.getText().toString();
            Usuario usuario = controlador.buscarUsuario(usu,contraseña);


            if (usuario != null) {
                SharedPreferences preferencesNombre = getSharedPreferences("nombre", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesNombre.edit();
                editor.putString("nombre", usuario.getNombre());
                editor.commit();
            } else {
                Toast.makeText(this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Verifique los campos", Toast.LENGTH_SHORT).show();
        }
        
        }

        public void registrar(View view){
            Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
            startActivity(intent);
        }
        
}
