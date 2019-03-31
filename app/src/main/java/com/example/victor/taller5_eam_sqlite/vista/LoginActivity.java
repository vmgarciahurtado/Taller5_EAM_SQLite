package com.example.victor.taller5_eam_sqlite.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
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

        Log.i("******DIRECCION IP", DebugDB.getAddressLog());
        campoUsuario = findViewById(R.id.campousuario);
        campoContraseña = findViewById(R.id.campoContraseña);
        controlador = new ControladorUsuario(this);
    }

    public void buscar(View view) {

        String usu,contraseña;
        usu = campoUsuario.getText().toString();
        contraseña = campoContraseña.getText().toString();
        
        if (!usu.equals("") && !contraseña.equals("") ){
            Usuario usuario = controlador.buscarUsuario(usu,contraseña);
            
            if (usuario != null) {
                SharedPreferences preferencesNombre = getSharedPreferences("nombre", Context.MODE_PRIVATE);
                SharedPreferences.Editor nombre = preferencesNombre.edit();
                nombre.putString("nombre", usuario.getNombre());
                nombre.commit();

                SharedPreferences preferencesUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor user = preferencesUsuario.edit();
                user.putString("usuario", usuario.getUsuario());
                user.commit();

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Verifique los campos", Toast.LENGTH_SHORT).show();
        }
        
        }

    public void registro(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistrarUsuarioActivity.class);
        startActivity(intent);
    }
}
